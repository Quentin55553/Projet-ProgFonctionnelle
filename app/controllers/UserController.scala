package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.{ExecutionContext, Future}
import repositories.UserRepository
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
    val passwordOpt = (request.body \ "password").asOpt[String]

    (usernameOpt, passwordOpt) match {
      case (Some(username), Some(password)) =>
        userRepository.authenticate(username, password).flatMap {
          case Some(_) =>
            Future.successful(Conflict(Json.obj("status" -> "error", "message" -> "Nom d'utilisateur déjà pris")))
          case None =>
            userRepository.addUser(username, password).map { userId =>
              Created(Json.obj("status" -> "success", "userId" -> userId))
            }
        }
      case _ => Future.successful(BadRequest(Json.obj("status" -> "error", "message" -> "Nom d'utilisateur ou mot de passe manquant")))
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
          case Some(user) => Ok(Json.obj("status" -> "success", "message" -> s"Bienvenue, ${user.username}"))
          case None => Unauthorized(Json.obj("status" -> "error", "message" -> "Nom d'utilisateur ou mot de passe incorrect"))
        }
      case _ => Future.successful(BadRequest(Json.obj("status" -> "error", "message" -> "Paramètres manquants")))
    }
  }
}
