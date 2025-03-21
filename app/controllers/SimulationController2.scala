package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json.Json

import java.time.{LocalDate, LocalDateTime, ZoneOffset}
import scala.concurrent.{ExecutionContext, Future}
import Repository.UserRepository
import models.{Prevision, PriceDate, Simulation}

@Singleton
class SimulationController2 @Inject()(cc: ControllerComponents, userRepository: UserRepository)
                                    (implicit ec: ExecutionContext) extends AbstractController(cc) {

  /**
   * Endpoint: GET /simulate?userId=...&portfolioName=...&from=YYYY-MM-DD&to=YYYY-MM-DD
   * Exemple d'appel: /simulate?userId=8&portfolioName=Portefeuille%20Actions&from=2023-01-01&to=2023-03-01
   */
  def simulatePortfolio: Action[AnyContent] = Action.async { request =>
    // Récupère les paramètres de requête
    val userIdOpt = request.getQueryString("userId").flatMap(s => scala.util.Try(s.toInt).toOption)
    val portfolioNameOpt = request.getQueryString("portfolioName")
    val fromDateOpt = request.getQueryString("from").flatMap(s => scala.util.Try(LocalDate.parse(s)).toOption)
    val toDateOpt = request.getQueryString("to").flatMap(s => scala.util.Try(LocalDate.parse(s)).toOption)

    (userIdOpt, portfolioNameOpt, fromDateOpt, toDateOpt) match {
      case (Some(userId), Some(portfolioName), Some(fromDate), Some(toDate)) =>
        // Récupérer les portefeuilles de l'utilisateur
        userRepository.getUserPortfolios(userId).flatMap { userPortfoliosList =>
          // On cherche le portefeuille correspondant au nom fourni
          val maybePortfolio = userPortfoliosList.flatMap(_.portfolios.values).find(_.name == portfolioName)
          maybePortfolio match {
            case Some(portfolio) =>
              // Pour chaque actif du portefeuille, on récupère l'historique
              // On utilise la méthode DataFetcher.fetchHistoricalPrices, déjà définie
              // Ici, on suppose qu'elle renvoie List[PriceDate] pour un actif donné
              val historicalDataByAsset: Seq[(String, List[PriceDate])] = portfolio.assets.map { case (symbol, quantity) =>
                // Par exemple, pour AAPL, récupérer l'historique de 2023-01-01 à 2023-03-01
                val history = DataFetcher.fetchHistoricalPrices(symbol, fromDate, toDate)
                (symbol, history)
              }.toSeq

              // Combiner ces historiques pour obtenir une évolution globale du portefeuille
              // Pour chaque date, on calcule la somme (prix de l'actif * quantité) puis la valeur nette (somme / quantité totale)
              val dateMap = scala.collection.mutable.Map[LocalDate, (Double, Int)]()
              historicalDataByAsset.foreach { case (symbol, priceDates) =>
                val quantity = portfolio.assets(symbol)
                priceDates.foreach { pd =>
                  val (sum, qtySum) = dateMap.getOrElse(pd.date, (0.0, 0))
                  dateMap(pd.date) = (sum + pd.price * quantity, qtySum + quantity)
                }
              }
              val overallHistory: List[PriceDate] = dateMap.toList.sortBy(_._1).map { case (date, (totalValue, totalQty)) =>
                PriceDate(date, if (totalQty != 0) totalValue / totalQty else 0)
              }

              // Appliquer les prévisions : par exemple, 10 jours de prévision via régression linéaire et moyenne mobile.
              val simulation = new Simulation(overallHistory, riskFreeRate = 0.01)
              val futureDays = 10
              // On construit la liste de prix "pure" (Double) depuis overallHistory
              val priceList = overallHistory.map(_.price)

              // On crée une instance de Prevision
              val prevision = new Prevision(priceList)

              // On calcule les prédictions
              val predictedPricesRegression = prevision.predictFuturePricesWithRegression(futureDays)
              val predictedPricesMA = prevision.predictFuturePricesWithMovingAverage(7, futureDays)


              val responseJson = Json.obj(
                "historical" -> Json.toJson(overallHistory.map(pd => Json.obj("date" -> pd.date.toString, "price" -> pd.price))),
                "predictedRegression" -> predictedPricesRegression,
                "predictedMovingAverage" -> predictedPricesMA
              )
              Future.successful(Ok(responseJson))

            case None =>
              Future.successful(NotFound(Json.obj("error" -> "Portefeuille non trouvé")))
          }
        }
      case _ =>
        Future.successful(BadRequest(Json.obj("error" -> "Paramètres manquants ou invalides")))
    }
  }
}
