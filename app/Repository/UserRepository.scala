package Repository

import models.{Portfolio, User, UserPortfolios}
import org.mindrot.jbcrypt.BCrypt
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape
import sttp.monad.syntax.MonadErrorOps

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import scala.util.matching.Regex

@Singleton
class UserRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  protected val dbConfig = dbConfigProvider.get[JdbcProfile]
  import slick.jdbc.PostgresProfile.api._

  // --- Tables ---

  class UsersTable(tag: Tag) extends Table[User](tag, "users") {
    def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def username: Rep[String] = column[String]("username", O.Unique)
    def password_hash: Rep[String] = column[String]("password_hash")
    def email: Rep[String] = column[String]("email", O.Unique)

    def * : ProvenShape[User] =
      (id.?, username, password_hash, email) <> ((User.apply _).tupled, User.unapply)
  }

  class PortefeuilleTable(tag: Tag) extends Table[(Int, Int, String)](tag, "portefeuille") {
    def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def userId: Rep[Int] = column[Int]("user_id")
    def name: Rep[String] = column[String]("name")

    def userFk = foreignKey("user_fk", userId, users)(_.id, onDelete = ForeignKeyAction.Cascade)
    def * = (id, userId, name)
  }

  class PortefeuilleAssetsTable(tag: Tag) extends Table[(Int, Int, String, Int)](tag, "portefeuille_assets") {
    def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def portefeuilleId: Rep[Int] = column[Int]("portefeuille_id")
    def assetSymbol: Rep[String] = column[String]("asset_symbol")
    def quantity: Rep[Int] = column[Int]("quantity")

    def portefeuilleFk = foreignKey("portefeuille_fk", portefeuilleId, portefeuille)(_.id, onDelete = ForeignKeyAction.Cascade)
    def * = (id, portefeuilleId, assetSymbol, quantity)
  }

  // --- TableQuery ---

  lazy val users = TableQuery[UsersTable]
  lazy val portefeuille = TableQuery[PortefeuilleTable]
  lazy val portefeuilleAssets = TableQuery[PortefeuilleAssetsTable]

  // --- Regex email ---

  private val emailRegex: Regex = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$".r
  def isValidEmail(email: String): Boolean = emailRegex.matches(email)

  // --- Méthodes ---

  // Ajouter un utilisateur
  def addUser(username: String, email: String, password: String): Future[Either[String, Int]] = {
    if (!isValidEmail(email)) {
      Future.successful(Left("Email invalide. Veuillez entrer une adresse email valide."))
    } else {
      val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
      val insertQuery = (users returning users.map(_.id)) += User(None, username, hashedPassword, email)
      dbConfig.db.run(insertQuery).map(Right(_))
    }
  }

  // Authentification utilisateur
  def authenticate(username: String, password: String): Future[Option[User]] = {
    val query = users.filter(_.username === username).result.headOption
    dbConfig.db.run(query).map {
      case Some(user) if BCrypt.checkpw(password, user.password_hash) => Some(user)
      case _ => None
    }
  }

  // Recherche d'un utilisateur par username
  def findByUsername(username: String): Future[Option[User]] = {
    val query = users.filter(_.username === username).result.headOption
    dbConfig.db.run(query)
  }

  // Récupération des portefeuilles d'un utilisateur
  def getUserPortfolios(userId: Int): Future[List[UserPortfolios]] = {
    val query = portefeuille.filter(_.userId === userId)
      .join(portefeuilleAssets).on(_.id === _.portefeuilleId)
      .map { case (p, asset) => (p.id, p.name, asset.assetSymbol, asset.quantity) }
      .result

    dbConfig.db.run(query).map { rows =>
      // rows : Seq[(Int, String, String, Int)]
      //        (portId, portName, symbol, quantity)

      // On regroupe par portId
      val grouped = rows.groupBy(_._1) // Map[portId -> Seq(...)]

      grouped.map { case (portId, list) =>
        // portId identique, on prend le nom du portefeuille
        val portName = list.head._2

        // On fusionne tous les symboles
        // symbol -> somme des quantités
        val assetsMap = list.groupMapReduce(_._3)(_._4)(_ + _)
        // => ex: { "BTC" -> 2, "ETH" -> 10, ... }

        // On construit un objet Portfolio
        val portfolioObj = Portfolio(portName, assetsMap)

        // On renvoie un seul UserPortfolios avec 1 seul portefeuille
        // (portName -> portfolioObj)
        UserPortfolios(portId.toString, Map(portName -> portfolioObj))
      }.toList
    }
  }


  def createPortfolio(userId: Int, portfolioName: String): Future[Either[String, Int]] = {
    val insertQuery = (portefeuille returning portefeuille.map(_.id)) += (0, userId, portfolioName)
    dbConfig.db.run(insertQuery).map(Right(_)).recover {
      case ex => Left(s"Erreur lors de la création du portefeuille: ${ex.getMessage}")
    }
  }

  // Ajouter un actif
  def addAssetToPortfolio(userId: Int, portfolioName: String, symbol: String, quantity: Int): Future[Either[String, Unit]] = {
    // 1) Récupère l'ID du portefeuille correspondant à userId + portfolioName
    val action = for {
      maybePortId <- portefeuille
        .filter(p => p.userId === userId && p.name === portfolioName)
        .map(_.id)
        .result
        .headOption

      result <- maybePortId match {
        case Some(portId) =>
          // 2) Vérifie si une ligne (portId, symbol) existe déjà dans portefeuille_assets
          portefeuilleAssets
            .filter(a => a.portefeuilleId === portId && a.assetSymbol === symbol)
            // On récupère (id, portefeuilleId, assetSymbol, quantity)
            .map(x => (x.id, x.portefeuilleId, x.assetSymbol, x.quantity))
            .result
            .headOption
            .flatMap {
              case Some((existingId, _, _, oldQty)) =>
                // => On met à jour la quantité
                val newQty = oldQty + quantity
                if (newQty <= 0) {
                  // Si la quantité devient <= 0, on supprime la ligne
                  portefeuilleAssets
                    .filter(_.id === existingId)
                    .delete
                    .map(_ => Right(()): Either[String, Unit])
                } else {
                  // Sinon, on met à jour la colonne quantity
                  portefeuilleAssets
                    .filter(_.id === existingId)
                    .map(_.quantity)
                    .update(newQty)
                    .map(_ => Right(()): Either[String, Unit])
                }

              case None =>
                // Aucune ligne existante => on insère
                portefeuilleAssets
                  .map(a => (a.portefeuilleId, a.assetSymbol, a.quantity))
                  .+=( (portId, symbol, quantity) )
                  .map(_ => Right(()): Either[String, Unit])
            }

        case None =>
          DBIO.successful(Left("Portefeuille non trouvé pour cet utilisateur."))
      }
    } yield result

    dbConfig.db.run(action.transactionally).recover {
      case ex => Left(s"Erreur lors de l'ajout/mise à jour de l'actif : ${ex.getMessage}")
    }
  }

  // ==============================
  // SUPPRESSION d'un actif
  // ==============================
  def removeAssetFromPortfolio(userId: Int, portfolioName: String, symbol: String): Future[Either[String, Unit]] = {
    // 1) On récupère l'ID du portefeuille correspondant
    val action = for {
      maybePortfolioId <- portefeuille
        .filter(p => p.userId === userId && p.name === portfolioName)
        .map(_.id)
        .result
        .headOption

      result <- maybePortfolioId match {
        case Some(portId) =>
          // 2) Supprimer la ligne dans portefeuille_assets
          portefeuilleAssets
            .filter(a => a.portefeuilleId === portId && a.assetSymbol === symbol)
            .delete
            .map(_ => Right(()))

        case None =>
          DBIO.successful(Left("Portefeuille non trouvé pour cet utilisateur."))
      }
    } yield result

    dbConfig.db.run(action.transactionally).recover {
      case ex => Left(s"Erreur lors de la suppression de l'actif : ${ex.getMessage}")
    }
  }
}
