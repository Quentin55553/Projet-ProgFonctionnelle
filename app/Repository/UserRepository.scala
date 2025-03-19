package Repository

import models.User
import org.mindrot.jbcrypt.BCrypt
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import scala.util.matching.Regex


@Singleton
class UserRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  protected val dbConfig = dbConfigProvider.get[JdbcProfile]

  import slick.jdbc.PostgresProfile.api._


  class UsersTable(tag: Tag) extends Table[User](tag, "users") {
    def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def username: Rep[String] = column[String]("username", O.Unique)
    def password_hash: Rep[String] = column[String]("password_hash")
    def email = column[String]("email", O.Unique)

    def * : ProvenShape[User] = (id.?, username, password_hash, email) <> ((User.apply _).tupled, User.unapply)
  }


  lazy val users = TableQuery[UsersTable]
  private val emailRegex: Regex = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$".r


  def isValidEmail(email: String): Boolean = emailRegex.matches(email)


  def addUser(username: String, email: String, password: String): Future[Either[String, Int]] = {
    if (!isValidEmail(email)) {
      Future.successful(Left("Email invalide. Veuillez entrer une adresse email valide."))

    } else {
      val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
      val insertQuery = (users returning users.map(_.id)) += User(None, username, hashedPassword, email)
      dbConfig.db.run(insertQuery).map(Right(_))
    }
  }


  def authenticate(username: String, password: String): Future[Option[User]] = {
    val query = users.filter(_.username === username).result.headOption

    dbConfig.db.run(query).map {
      case Some(user) if BCrypt.checkpw(password, user.password_hash) => Some(user)
      case _ => None
    }
  }
}
