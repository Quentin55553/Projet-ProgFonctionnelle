import cats.effect._
import doobie._
import doobie.implicits._
import doobie.util.transactor.Transactor
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.server.middleware.Logger
import org.http4s.UrlForm
import org.http4s.Charset
import org.http4s.MediaType
import org.http4s.headers.`Content-Type`
import java.nio.charset.StandardCharsets
import scala.io.Source

// Import de la méthode d'ajout d'utilisateur depuis Database.scala
import Database.addUser

object WebLogin extends IOApp {

  // Transactor pour PostgreSQL.
  val xa = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver",
    "jdbc:postgresql://localhost:5432/postgres",
    "postgres",
    "root"
  )

  // Représentation d'un utilisateur (id, username, password_hash).
  case class User(id: Int, username: String, passwordHash: String)

  // Vérification de la connexion avec username et password.
  def checkUser(username: String, password: String): IO[Boolean] =
    sql"SELECT COUNT(*) FROM users WHERE username = $username AND password_hash = $password"
      .query[Int]
      .unique
      .transact(xa)
      .map(_ > 0)

  // Récupère tous les utilisateurs.
  def getAllUsers(): IO[List[User]] =
    sql"SELECT id, username, password_hash FROM users"
      .query[User]
      .to[List]
      .transact(xa)

  // Charge une vue depuis le classpath.
  def loadView(viewPath: String): IO[String] = IO {
    val resourceUrl = getClass.getClassLoader.getResource(viewPath)
    if (resourceUrl == null)
      throw new Exception(s"Resource $viewPath introuvable dans le classpath")
    val source = Source.fromURL(resourceUrl)(StandardCharsets.UTF_8)
    try source.mkString finally source.close()
  }

  // Génère les lignes HTML pour le tableau des utilisateurs.
  def generateTableRows(users: List[User]): String =
    users.map { user =>
      s"<tr><td>${user.id}</td><td>${user.username}</td><td>${user.passwordHash}</td></tr>"
    }.mkString("\n")

  // Remplace le placeholder "{{rows}}" dans le template.
  def renderUserTable(template: String, rows: String): String =
    template.replace("{{rows}}", rows)

  // Définition des routes HTTP.
  val routes = HttpRoutes.of[IO] {

    // GET / affiche la page de connexion et inscription.
    case GET -> Root =>
      for {
        html <- loadView("views/login.html")
        resp <- Ok(html).map(_.withContentType(`Content-Type`(MediaType.text.html, Charset.`UTF-8`)))
      } yield resp

    // POST /login traite le formulaire de connexion.
    case req @ POST -> Root / "login" =>
      for {
        form     <- req.as[UrlForm]
        username = form.values.get("username").flatMap(_.headOption).getOrElse("")
        password = form.values.get("password").flatMap(_.headOption).getOrElse("")
        valid    <- checkUser(username, password)
        resp     <- if (valid) {
          for {
            users    <- getAllUsers()
            rows      = generateTableRows(users)
            template <- loadView("views/userTable.html")
            rendered  = renderUserTable(template, rows)
            response <- Ok(rendered).map(_.withContentType(`Content-Type`(MediaType.text.html, Charset.`UTF-8`)))
          } yield response
        } else {
          Ok("Nom d'utilisateur ou mot de passe incorrect.")
            .map(_.withContentType(`Content-Type`(MediaType.text.plain, Charset.`UTF-8`)))
        }
      } yield resp

    // POST /register traite le formulaire d'inscription.
    case req @ POST -> Root / "register" =>
      for {
        form     <- req.as[UrlForm]
        username = form.values.get("username").flatMap(_.headOption).getOrElse("")
        password = form.values.get("password").flatMap(_.headOption).getOrElse("")
        _        <- addUser(username, password)  // Ajoute l'utilisateur dans la DB.
        response <- Ok("Inscription réussie ! Vous pouvez maintenant vous connecter.")
          .map(_.withContentType(`Content-Type`(MediaType.text.plain, Charset.`UTF-8`)))
      } yield response
  }

  // Construction de l'application HTTP.
  val httpApp = Router("/" -> routes).orNotFound

  // Démarrage du serveur HTTP.
  override def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO]
      .bindHttp(8080, "0.0.0.0")
      .withHttpApp(Logger.httpApp(true, true)(httpApp))
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
}
