package controllers

import javax.inject._
import play.api.mvc._
import models._
import java.time.LocalDate
import scala.concurrent.ExecutionContext

@Singleton
class SimulationController @Inject()(cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def index = Action {
    Ok(views.html.simulation())
  }

  def evaluate(date: String) = Action {
    try {
      val dateToEvaluate = LocalDate.parse(date)

      val fromDate = LocalDate.of(2025, 2, 14)
      val toDate = LocalDate.of(2025, 3, 14)
      val pricesDates = DataFetcher.fetchHistoricalPrices("AAPL", fromDate, toDate)
      val riskFreeRate = 0.01
      val simulation = new Simulation(pricesDates, riskFreeRate)

      val lastDate = pricesDates.last.date

      if (dateToEvaluate.isAfter(lastDate)) {
        val prices = pricesDates.map(_.price)
        val prevision = new Prevision(prices)
        val futureDays = (dateToEvaluate.toEpochDay - lastDate.toEpochDay).toInt

        val predictedPricesRegression = prevision.predictFuturePricesWithRegression(futureDays)
        val predictedPriceRegression = predictedPricesRegression.last

        val predictedPricesMA = prevision.predictFuturePricesWithMovingAverage(7, futureDays)
        val predictedPriceMA = predictedPricesMA.last

        Ok(views.html.results(predictedPriceRegression, predictedPriceMA))
      } else {
        val evaluation = simulation.evaluateIndicatorsForDate(dateToEvaluate)
        Ok(views.html.evaluation(evaluation))
      }
    } catch {
      case e: Exception => BadRequest("Date invalide")
    }
  }
}