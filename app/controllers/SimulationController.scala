package controllers

import models._
import java.time.LocalDate

object SimulationController extends App {

  val prices = List(
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
        var found = false
        var selectedPrices = List[Double]()
        for (priceDate <- prices) {
          if (priceDate.date.isBefore(dateToEvaluate) || priceDate.date.isEqual(dateToEvaluate)) {
            selectedPrices = selectedPrices :+ priceDate.price
          }
          if (priceDate.date.isEqual(dateToEvaluate)) {
            found = true
          }
        }

        if (found) {
          val indicators = IndicatorsMarket(selectedPrices)

          var returns = List[Double]()
          for (i <- 1 until selectedPrices.length) {
            val prev = selectedPrices(i - 1)
            val current = selectedPrices(i)
            returns = returns :+ ((current - prev) / prev)
          }

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
      }
    } catch {
      case e: Exception => println("Date invalide. Veuillez entrer une date correcte au format YYYY-MM-DD.")
    }

    println("\nEntrez une nouvelle date pour l'évaluation (au format YYYY-MM-DD) ou tapez 'exit' pour quitter :")
    inputDate = scala.io.StdIn.readLine()
  }

  println("Merci d'avoir utilisé la simulation. À bientôt !")
}