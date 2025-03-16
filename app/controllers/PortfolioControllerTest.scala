package controllers

import javax.inject._
import akka.actor.ActorRef
import akka.pattern.ask
import akka.util.Timeout
import Models._
import api.APIHandler
import play.api.libs.json._
import play.api.mvc._
import data._

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class PortfolioControllerTest @Inject() (
                                          val controllerComponents: ControllerComponents,
                                          @Named("portfolioManager") portfolioManager: ActorRef,
                                          apiHandler: APIHandler
                                        )(implicit ec: ExecutionContext) extends BaseController {

  implicit val timeout: Timeout = 5.seconds

  def addAsset(userId: String, symbol: String, quantity: Int): Action[AnyContent] = Action.async {
    portfolioManager ! AddAsset(userId, symbol, quantity)
    Future.successful(Ok(Json.obj("status" -> "success", "message" -> s"Added $quantity of $symbol to $userId's portfolio")))
  }

  def removeAsset(userId: String, symbol: String, quantity: Int): Action[AnyContent] = Action.async {
    portfolioManager ! RemoveAsset(userId, symbol, quantity)
    Future.successful(Ok(Json.obj("status" -> "success", "message" -> s"Removed $quantity of $symbol from $userId's portfolio")))
  }

  def getPortfolio(userId: String): Action[AnyContent] = Action.async {
    (portfolioManager ? GetPortfolio(userId)).mapTo[Portfolio].flatMap { portfolio =>
      val assetFutures = portfolio.assets.map { case (symbol, quantity) =>
        Future {
          apiHandler.fetchStockData(symbol).map { asset =>
            symbol -> Json.obj(
              "quantity" -> quantity,
              "price" -> asset.currentPrice,
              "totalValue" -> BigDecimal(quantity * asset.currentPrice).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
            )
          }.getOrElse(symbol -> Json.obj("quantity" -> quantity, "price" -> 0.0, "totalValue" -> 0.0))
        }
      }

      Future.sequence(assetFutures).map { assetList =>
        val assetsJson = JsObject(assetList.toSeq)
        Ok(Json.obj("userId" -> userId, "assets" -> assetsJson))
      }
    }
  }
}
