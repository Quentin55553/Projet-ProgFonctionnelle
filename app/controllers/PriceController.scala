package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json.Json
import api.APIHandler
import data.FinancialAsset

import java.time.LocalDate

@Singleton
class PriceController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  val cryptoSymbols = Set("BTC", "ETH", "BNB", "XRP", "ADA", "SOL", "DOGE", "DOT", "MATIC", "LTC")

  def getPrice: Action[AnyContent] = Action { request =>
    val symbolOpt = request.getQueryString("symbol")
    symbolOpt match {
      case Some(symbol) =>
        val api = new APIHandler()

        // On vérifie si c'est une crypto connue
        val maybeAsset: Option[FinancialAsset] =
          if (cryptoSymbols.contains(symbol.toUpperCase)) {
            // On appelle la méthode qui interroge Finnhub
            api.fetchRealCryptoData(symbol)
          } else {
            // Sinon on suppose que c'est une action
            api.fetchStockData(symbol)
          }

        maybeAsset match {
          case Some(asset) =>
            Ok(Json.obj("currentPrice" -> asset.currentPrice))
          case None =>
            NotFound(Json.obj("error" -> s"Symbol '$symbol' non trouvé"))
        }

      case None =>
        BadRequest(Json.obj("error" -> "Aucun symbole fourni en paramètre"))
    }
  }
  def getHistoricalData: Action[AnyContent] = Action { request =>
    val symbolOpt = request.getQueryString("symbol")
    symbolOpt match {
      case Some(symbol) =>
        // Ici, on génère ou fetch l'historique
        // Ex: on appelle DataFetcher.fetchHistoricalPrices(symbol, fromDate, toDate)
        val fromDate = LocalDate.of(2020, 1, 1)
        val toDate   = LocalDate.of(2020, 1, 8)
        val data     = DataFetcher.fetchHistoricalPrices(symbol, fromDate, toDate)

        // data est une List[PriceDate], ex: List(PriceDate(LocalDate, Double))
        // Convertissons en JSON { date, price }
        val jsonArray = data.map { pd =>
          Json.obj("date" -> pd.date.toString, "price" -> pd.price)
        }

        Ok(Json.toJson(jsonArray))

      case None =>
        BadRequest(Json.obj("error" -> "Aucun symbole fourni"))
    }
  }

}
