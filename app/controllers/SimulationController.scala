package controllers

import models._
import java.time.LocalDate
import scala.math.Ordered.orderingToOrdered

object SimulationController extends App {

  val prices = Seq(
    PriceDate(LocalDate.of(2023, 1, 1), 100.0),
    PriceDate(LocalDate.of(2023, 1, 2), 95.0),
    PriceDate(LocalDate.of(2023, 1, 3), 90.0),
    PriceDate(LocalDate.of(2023, 1, 4), 85.0),
    PriceDate(LocalDate.of(2023, 1, 5), 80.0),
    PriceDate(LocalDate.of(2023, 1, 6), 85.0),
    PriceDate(LocalDate.of(2023, 1, 7), 90.0),
    PriceDate(LocalDate.of(2023, 1, 8), 95.0),
    PriceDate(LocalDate.of(2023, 1, 9), 100.0),
    PriceDate(LocalDate.of(2023, 1, 10), 105.0),
    PriceDate(LocalDate.of(2023, 1, 11), 110.0),
    PriceDate(LocalDate.of(2023, 1, 12), 115.0),
    PriceDate(LocalDate.of(2023, 1, 13), 120.0),
    PriceDate(LocalDate.of(2023, 1, 14), 125.0),
    PriceDate(LocalDate.of(2023, 1, 15), 120.0),
    PriceDate(LocalDate.of(2023, 1, 16), 115.0),
    PriceDate(LocalDate.of(2023, 1, 17), 110.0),
    PriceDate(LocalDate.of(2023, 1, 18), 105.0),
    PriceDate(LocalDate.of(2023, 1, 19), 100.0),
    PriceDate(LocalDate.of(2023, 1, 20), 95.0),
    PriceDate(LocalDate.of(2023, 1, 21), 90.0),
    PriceDate(LocalDate.of(2023, 1, 22), 85.0),
    PriceDate(LocalDate.of(2023, 1, 23), 80.0),
    PriceDate(LocalDate.of(2023, 1, 24), 85.0),
    PriceDate(LocalDate.of(2023, 1, 25), 90.0),
    PriceDate(LocalDate.of(2023, 1, 26), 95.0)
  )

  println("Entrez une date pour l'évaluation (au format YYYY-MM-DD) ou tapez 'exit' pour quitter :")

  var inputDate = scala.io.StdIn.readLine()

  while (inputDate.toLowerCase != "exit") {
    try {
      val dateToEvaluate = LocalDate.parse(inputDate)

      if (prices.exists(_.date == dateToEvaluate)) {
        val selectedPrices = prices.takeWhile(_.date <= dateToEvaluate).map(_.price)

        val indicators = IndicatorsMarket(selectedPrices)

        val returns = selectedPrices.sliding(2).map { case Seq(prev, current) => (current - prev) / prev }.toSeq

        val financialAlgorithm = FinancialAlgorithm(
          assets = FinancialAlgorithmController.assets,
          liabilities = FinancialAlgorithmController.liabilities,
          portfolioReturns = returns,
          riskFreeRate = FinancialAlgorithmController.riskFreeRate
        )

        println(s"\nÉvaluation pour la date: $dateToEvaluate, prix: ${selectedPrices.last} €")
        println(indicators.evaluateRSI())
        println(indicators.evaluateMACD())
        println(s"Volatilité: ${financialAlgorithm.volatility().formatted("%.4f")}")
        println(s"Sharpe Ratio: ${financialAlgorithm.sharpeRatio().formatted("%.4f")}")

      } else {
        println("Date invalide. Aucun prix trouvé pour cette date.")
      }
    } catch {
      case e: Exception => println("Date invalide. Veuillez entrer une date correcte au format YYYY-MM-DD.")
    }

    println("\nEntrez une nouvelle date pour l'évaluation (au format YYYY-MM-DD) ou tapez 'exit' pour quitter :")
    inputDate = scala.io.StdIn.readLine()
  }

  println("Merci d'avoir utilisé la simulation. À bientôt !")
}
