package repositories

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.{JdbcProfile, PostgresProfile}
import slick.lifted.ProvenShape
import scala.concurrent.{ExecutionContext, Future}
import org.mindrot.jbcrypt.BCrypt
import models.User

@Singleton
class UserRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {

  // 🔥 Récupérer la configuration Slick
  protected val dbConfig = dbConfigProvider.get[JdbcProfile]
  import slick.jdbc.PostgresProfile.api._

  // Table des utilisateurs
  class UsersTable(tag: Tag) extends Table[User](tag, "users") {
    def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def username: Rep[String] = column[String]("username", O.Unique)
    def password_hash: Rep[String] = column[String]("password_hash")

    def * : ProvenShape[User] = (id.?, username, password_hash) <> ((User.apply _).tupled, User.unapply)
  }

  // Requête sur les utilisateurs
  lazy val users = TableQuery[UsersTable]

  // Ajouter un utilisateur
  def addUser(username: String, password: String): Future[Int] = {
    val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
    val insertQuery = (users returning users.map(_.id)) += User(None, username, hashedPassword)
    dbConfig.db.run(insertQuery)
  }

  // Vérifier l'authentification (login)
  def authenticate(username: String, password: String): Future[Option[User]] = {
    val query = users.filter(_.username === username).result.headOption
    dbConfig.db.run(query).map {
      case Some(user) if BCrypt.checkpw(password, user.password_hash) => Some(user)
      case _ => None
    }
  }
}
