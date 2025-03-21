package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import scala.concurrent.{ExecutionContext, Future}
import Repository.UserRepository
import models._

@Singleton
class PortfolioController @Inject()(cc: ControllerComponents, userRepository: UserRepository)
                                   (implicit ec: ExecutionContext) extends AbstractController(cc) {

  def getPortfolios(userId: Int): Action[AnyContent] = Action.async {
    userRepository.getUserPortfolios(userId).map { portfolios =>
      Ok(Json.toJson(portfolios))
    }
  }

  def createPortfolio: Action[JsValue] = Action.async(parse.json) { request =>
    val userIdOpt = (request.body \ "userId").asOpt[Int]
    val portfolioNameOpt = (request.body \ "portfolioName").asOpt[String]

    (userIdOpt, portfolioNameOpt) match {
      case (Some(userId), Some(portfolioName)) =>
        userRepository.createPortfolio(userId, portfolioName).map {
          case Right(portfolioId) =>
            Created(Json.obj("status" -> "success", "portfolioId" -> portfolioId))
          case Left(errorMessage) =>
            BadRequest(Json.obj("status" -> "error", "message" -> errorMessage))
        }
      case _ =>
        Future.successful(BadRequest(Json.obj("status" -> "error", "message" -> "Données invalides")))
    }
  }

  def addAsset: Action[JsValue] = Action.async(parse.json) { request =>
    val userIdOpt = (request.body \ "userId").asOpt[Int]
    val portfolioNameOpt = (request.body \ "portfolioName").asOpt[String]
    val symbolOpt = (request.body \ "symbol").asOpt[String]
    val quantityOpt = (request.body \ "quantity").asOpt[Int]

    (userIdOpt, portfolioNameOpt, symbolOpt, quantityOpt) match {
      case (Some(userId), Some(portfolioName), Some(symbol), Some(quantity)) =>
        userRepository.addAssetToPortfolio(userId, portfolioName, symbol, quantity).map {
          case Right(_) =>
            Ok(Json.obj("status" -> "success", "message" -> "Actif ajouté avec succès"))
          case Left(errorMessage) =>
            BadRequest(Json.obj("status" -> "error", "message" -> errorMessage))
        }
      case _ =>
        Future.successful(BadRequest(Json.obj("status" -> "error", "message" -> "Données invalides")))
    }
  }

  // =======================================
  // SUPPRESSION d’un actif
  // =======================================
  def removeAsset: Action[JsValue] = Action.async(parse.json) { request =>
    val userIdOpt = (request.body \ "userId").asOpt[Int]
    val portfolioNameOpt = (request.body \ "portfolioName").asOpt[String]
    val symbolOpt = (request.body \ "symbol").asOpt[String]

    (userIdOpt, portfolioNameOpt, symbolOpt) match {
      case (Some(userId), Some(portfolioName), Some(symbol)) =>
        userRepository.removeAssetFromPortfolio(userId, portfolioName, symbol).map {
          case Right(_) =>
            Ok(Json.obj("status" -> "success", "message" -> "Actif supprimé avec succès"))
          case Left(errorMessage) =>
            BadRequest(Json.obj("status" -> "error", "message" -> errorMessage))
        }
      case _ =>
        Future.successful(BadRequest(Json.obj("status" -> "error", "message" -> "Données invalides")))
    }
  }
}
