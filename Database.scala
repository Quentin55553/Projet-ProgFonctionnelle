import cats.effect.{IO, Resource}
import doobie._
import doobie.hikari._
import doobie.implicits._
import org.mindrot.jbcrypt.BCrypt // Ajout de l'import pour BCrypt
import scala.concurrent.ExecutionContext

object Database {
  // Contexte pour l'exécution IO
  val connectEC = ExecutionContext.global

  // Déclaration du transactor comme une Resource
  val transactorResource: Resource[IO, HikariTransactor[IO]] = HikariTransactor.newHikariTransactor[IO](
    "org.postgresql.Driver",                // Driver PostgreSQL
    "jdbc:postgresql://localhost:5432/postgres", // URL de connexion
    "postgres",                             // Nom d'utilisateur
    "root",                           // Mot de passe
    connectEC                               // ExecutionContext
  )

  // Représentation de la table 'users' sous forme de case class
  case class User(id: Option[Int], username: String, password_hash: String)

  // Définir la manière dont Doobie mappe les colonnes de la table vers la case class User
  implicit val userRead: Read[User] = Read[(Option[Int], String, String)].map {
    case (id, username, password_hash) => User(id, username, password_hash)
  }

  // Méthode pour hacher un mot de passe
  def hashPassword(password: String): String = {
    BCrypt.hashpw(password, BCrypt.gensalt())
  }

  // Méthode pour ajouter un utilisateur à la base de données
  def addUser(username: String, password: String): IO[Unit] = {
    val hashedPassword = hashPassword(password)

    // Utilisation de 'use' pour obtenir le Transactor à partir de Resource
    transactorResource.use { transactor =>
      val query = sql"""
      INSERT INTO users (username, password_hash)
      VALUES ($username, $hashedPassword)
    """.update.run.transact(transactor)

      // On renvoie un IO[Unit] ici
      query.map {
        case 1 => println(s"Utilisateur '$username' ajouté avec succès.")
        case _ => println(s"Échec de l'ajout de l'utilisateur '$username'.")
      }.as(()) // Retourne un IO[Unit]
    }
  }


  // Exemple d'utilisation pour ajouter un utilisateur
  def testAddUser(): IO[Unit] = {
    addUser("aminefilali", "secure_password123456")
  }

  // Méthode de test de la connexion
  def testConnection: IO[Unit] = {
    transactorResource.use { transactor =>
      sql"SELECT 1".query[Int].unique.transact(transactor).map { result =>
        println(s"Connexion réussie ! Résultat : $result")
      }
    }
  }
}
