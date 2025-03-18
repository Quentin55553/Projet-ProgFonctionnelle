import cats.effect._
import doobie._
import doobie.implicits._
import doobie.util.transactor.Transactor
import scala.io.StdIn

object LoginApp extends IOApp {

  val xa = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver", // Driver PostgreSQL
    "jdbc:postgresql://localhost:5432/postgres", // URL de connexion
    "postgres", // Nom d'utilisateur
    "root",
  )

  def checkUser(username: String, password: String): IO[Boolean] = {
    sql"SELECT COUNT(*) FROM users WHERE username = $username AND password_hash = $password"
      .query[Int]
      .unique
      .transact(xa)
      .map(_ > 0)
  }

  def run(args: List[String]): IO[ExitCode] = {
    for {
      _ <- IO(println("Entrez votre nom d'utilisateur :"))
      username <- IO(StdIn.readLine())
      _ <- IO(println("Entrez votre mot de passe :"))
      password <- IO(StdIn.readLine())
      isValid <- checkUser(username, password)
      _ <- if (isValid) IO(println("Connexion réussie !")) else IO(println("Nom d'utilisateur ou mot de passe incorrect."))
    } yield ExitCode.Success
  }
}
