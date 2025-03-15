package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.{ExecutionContext, Future}
import repositories.UserRepository // 🔥 Import du UserRepository

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents, userRepository: UserRepository)
                              (implicit ec: ExecutionContext) extends BaseController {

    // Définition du format JSON attendu pour le login
    case class LoginRequest(username: String, password: String)
    implicit val loginRequestReads: Reads[LoginRequest] = Json.reads[LoginRequest]

    // 📌 Affiche la page HTML de connexion
    def servePage(page: String): Action[AnyContent] = Action {
        Ok.sendFile(new java.io.File(s"public/html/$page"))
    }

    // 📌 Vérifie les identifiants et connecte l'utilisateur
    def login: Action[JsValue] = Action.async(parse.json) { implicit request =>
        request.body.validate[LoginRequest] match {
            case JsSuccess(loginData, _) =>
                userRepository.authenticate(loginData.username, loginData.password).map {
                    case Some(user) => Ok(Json.obj("status" -> "success", "message" -> "Connexion réussie"))
                    case None       => Unauthorized(Json.obj("status" -> "error", "message" -> "Identifiants incorrects"))
                }

            case JsError(errors) =>
                Future.successful(BadRequest(Json.obj("status" -> "error", "message" -> "Requête invalide")))
        }
    }

    // 📌 Page d'accueil après connexion
    def index(username: String): Action[AnyContent] = Action {
        Ok(s"Bienvenue, $username !")
    }

    // 📌 Affiche la page HTML de connexion
    def serveLoginPage: Action[AnyContent] = Action {
        Ok.sendFile(new java.io.File("public/html/login.html"))
    }

}

