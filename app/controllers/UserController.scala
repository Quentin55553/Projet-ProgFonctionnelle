package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.{ExecutionContext, Future}
import Repository.UserRepository
import models.User

@Singleton
class UserController @Inject()(cc: ControllerComponents, userRepository: UserRepository)(implicit ec: ExecutionContext) extends AbstractController(cc) {
  implicit val userFormat: OFormat[User] = Json.format[User]

  /**
   * Inscription d'un nouvel utilisateur
   * @return ID de l'utilisateur créé ou une erreur
   */
  def register: Action[JsValue] = Action.async(parse.json) { request =>
    val usernameOpt = (request.body \ "username").asOpt[String]
    val emailOpt = (request.body \ "email").asOpt[String]
    val passwordOpt = (request.body \ "password").asOpt[String]

    (usernameOpt, emailOpt, passwordOpt) match {
      case (Some(username), Some(email), Some(password)) =>
        userRepository.addUser(username, email, password).map {
          case Right(userId) => Created(Json.obj("status" -> "success", "userId" -> userId))
          case Left(errorMessage) => BadRequest(Json.obj("status" -> "error", "message" -> errorMessage))
        }
      case _ => Future.successful(BadRequest(Json.obj("status" -> "error", "message" -> "Données incomplètes")))
    }
  }
  /**
   * Connexion d'un utilisateur
   * @return Succès ou échec de l'authentification
   */
  def login: Action[JsValue] = Action.async(parse.json) { request =>
    val usernameOpt = (request.body \ "username").asOpt[String]
    val passwordOpt = (request.body \ "password").asOpt[String]

    (usernameOpt, passwordOpt) match {
      case (Some(username), Some(password)) =>
        userRepository.authenticate(username, password).map {
          case Some(user) =>
            Ok(Json.obj("status" -> "success", "message" -> "Connexion réussie", "username" -> user.username))
              .withSession("username" -> user.username)

          case None =>
            Unauthorized(Json.obj("status" -> "error", "message" -> "Nom d'utilisateur ou mot de passe incorrect"))
        }

      case _ =>
        Future.successful(BadRequest(Json.obj("status" -> "error", "message" -> "Paramètres manquants")))
    }
  }
}
