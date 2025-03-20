package controllers

import java.time.LocalDate
import javax.inject._
import play.api.mvc._
import models._
import scala.concurrent.ExecutionContext
import play.api.i18n.I18nSupport

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents)(implicit ec: ExecutionContext)
  extends BaseController with I18nSupport {

  private val prices = DataFetcher.fetchHistoricalPrices("AAPL", LocalDate.of(2025, 2, 14), LocalDate.of(2025, 3, 14))
  private val simulation = new Simulation(prices, riskFreeRate = 0.01)

  def simulationPage() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.simulation(prices))
  }

  def evaluateDate() = Action { implicit request: Request[AnyContent] =>
    val dateString = request.body.asFormUrlEncoded.get("date").head
    val date = LocalDate.parse(dateString)

    if (date.isAfter(prices.last.date)) {
      val prevision = new Prevision(prices.map(_.price))
      val futureDays = (date.toEpochDay - prices.last.date.toEpochDay).toInt
      val predictedPricesRegression = prevision.predictFuturePricesWithRegression(futureDays)
      val predictedPricesMA = prevision.predictFuturePricesWithMovingAverage(7, futureDays)

      Ok(views.html.prevision(date, predictedPricesRegression.last, predictedPricesMA.last))
    } else {
      val selectedPrices = prices.takeWhile(_.date.isBefore(date.plusDays(1))).map(_.price)
      val financialMetrics = FinancialMetrics(selectedPrices, riskFreeRate = 0.01)

      // Calculer séparément les résultats du RSI et du MACD
      val rsiResult = simulation.evaluateRSI(selectedPrices)
      val macdResult = simulation.evaluateMACD(selectedPrices)

      Ok(views.html.results(
        date,
        prices.find(_.date.isEqual(date)).get.price,
        rsiResult,
        macdResult,
        financialMetrics.volatility(),
        financialMetrics.sharpeRatio()
      ))
    }
  }


}