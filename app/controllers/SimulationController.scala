package controllers

import models._
import java.time.LocalDate
import models.PriceDate
import scala.annotation.tailrec

object SimulationController extends App {

  val fromDate = LocalDate.of(2025, 2, 14)
  val toDate = LocalDate.of(2025, 3, 14)
  val prices = DataFetcher.fetchHistoricalPrices("AAPL", fromDate, toDate)

  val lastDate = prices.last.date

  println("Entrez une date pour l'évaluation (au format YYYY-MM-DD) ou tapez 'exit' pour quitter :")
  var inputDate = scala.io.StdIn.readLine()

  while (inputDate.toLowerCase != "exit") {
    try {
      val dateToEvaluate = LocalDate.parse(inputDate)

      if (dateToEvaluate.isAfter(lastDate)) {
        val selectedPrices = prices.map(_.price)
        val prevision = new Prevision(selectedPrices)

        val futureDays = (dateToEvaluate.toEpochDay - lastDate.toEpochDay).toInt

        val predictedPricesRegression = prevision.predictFuturePricesWithRegression(futureDays)
        val predictedPriceRegression = predictedPricesRegression.last

        val predictedPricesMA = prevision.predictFuturePricesWithMovingAverage(7, futureDays)
        val predictedPriceMA = predictedPricesMA.last

        println(s"\nPrédictions pour la date: $dateToEvaluate")
        println(s"1. Prix prédit (Régression Linéaire): $predictedPriceRegression €")
        println(s"2. Prix prédit (Moyenne Mobile - 7 jours): $predictedPriceMA €")
      } else {
        prices.find(_.date == dateToEvaluate) match {
          case Some(priceDate) =>
            val selectedPrices = prices.takeWhile(_.date.isBefore(dateToEvaluate.plusDays(1))).map(_.price)
            val indicators = IndicatorsMarket(selectedPrices)

            val returns = selectedPrices.sliding(2).map { case List(prev, current) =>
              (current - prev) / prev
            }.toList

            val financialAlgorithm = FinancialAlgorithm(
              assets = FinancialAlgorithmController.assets,
              liabilities = FinancialAlgorithmController.liabilities,
              portfolioReturns = returns,
              riskFreeRate = FinancialAlgorithmController.riskFreeRate
            )

            println(s"\nÉvaluation pour la date: $dateToEvaluate, prix: ${priceDate.price} €")
            println(indicators.evaluateRSI())
            println(indicators.evaluateMACD())
            println(s"Volatilité: ${financialAlgorithm.volatility().formatted("%.4f")}")
            println(s"Sharpe Ratio: ${financialAlgorithm.sharpeRatio().formatted("%.4f")}")

          case None =>
            println("Date invalide. Aucun prix trouvé pour cette date.")
        }
      }
    } catch {
      case e: Exception => println("Date invalide. Veuillez entrer une date correcte au format YYYY-MM-DD.")
    }

    println("\nEntrez une nouvelle date pour l'évaluation (au format YYYY-MM-DD) ou tapez 'exit' pour quitter :")
    inputDate = scala.io.StdIn.readLine()
  }

  println("Merci d'avoir utilisé la simulation. À bientôt !")
}