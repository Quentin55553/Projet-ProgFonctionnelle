package controllers
/*
import javax.inject._
import akka.actor.ActorRef
import akka.pattern.ask
import akka.util.Timeout
import api.APIHandler
import data.FinancialAsset.readJsonAsFinancialAsset
import play.api.libs.json._
import play.api.mvc._
import data._
import models.{AddAsset, GetPortfolio, Portfolio, RemoveAsset}
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json.OFormat.oFormatFromReadsAndOWrites

import java.time.LocalDateTime
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

  def getCryptoPrice(symbol: String): Action[AnyContent] = Action {
    apiHandler.fetchCryptoData(symbol) match {
      case Some(asset) => Ok(Json.obj("symbol" -> symbol, "price" -> asset.currentPrice))
      case None => BadRequest("Crypto non supportée")
    }
  }

  def getForexPrice(symbol: String): Action[AnyContent] = Action {
    apiHandler.fetchForexData(symbol) match {
      case Some(asset) => Ok(Json.obj("symbol" -> symbol, "price" -> asset.currentPrice))
      case None => BadRequest("Paire Forex non supportée")
    }
  }

  def getHistoricalData(symbol: String, from: String, to: String): Action[AnyContent] = Action {
    val fromDate = LocalDateTime.parse(from)
    val toDate = LocalDateTime.parse(to)

    apiHandler.fetchFinancialAssetData(symbol, fromDate, toDate) match {
      case Some(data) => Ok(Json.toJson(data))
      case None => BadRequest("Données historiques non disponibles")
    }
  }


  def getPortfolio(userId: String): Action[AnyContent] = Action.async {
    (portfolioManager ? GetPortfolio(userId)).mapTo[Portfolio].flatMap { portfolio =>
      val assetFutures = portfolio.assets.map { case (symbol, quantity) =>
        Future {
          val assetOpt = apiHandler.fetchStockData(symbol)
            .orElse(apiHandler.fetchCryptoData(symbol))
            .orElse(apiHandler.fetchForexData(symbol))

          assetOpt.map { asset =>
            symbol -> Json.obj(
              "quantity" -> quantity,
              "price" -> asset.currentPrice,
              "totalValue" -> BigDecimal(quantity * asset.currentPrice).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
            )
          }.getOrElse(symbol -> Json.obj("quantity" -> quantity, "price" -> 0.0, "totalValue" -> 0.0)) // Si aucun prix trouvé
        }
      }

      Future.sequence(assetFutures).map { assetList =>
        val assetsJson = JsObject(assetList.toSeq)
        Ok(Json.obj("userId" -> userId, "assets" -> assetsJson))
      }
    }
  }
}
*/