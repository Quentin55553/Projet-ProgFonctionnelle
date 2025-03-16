package controllers

import models._
import java.time.LocalDate

object SimulationController extends App {

  val fromDate = LocalDate.of(2025, 2, 14)
  val toDate = LocalDate.of(2025, 3, 14)
  val prices = DataFetcher.fetchHistoricalPrices("AAPL", fromDate, toDate)

  val riskFreeRate = 0.01

  val simulation = new Simulation(prices, riskFreeRate)

  println("Prix historiques récupérés :")
  prices.foreach { priceDate =>
    println(s"${priceDate.date} : prix = ${priceDate.price} €")
  }
  println()

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

            val returns = selectedPrices.sliding(2).map { case List(prev, current) =>
              (current - prev) / prev
            }.toList

            val indicators = IndicatorsMarket(selectedPrices)
            val financialMetrics = FinancialMetrics(selectedPrices, riskFreeRate)
            println(s"\nÉvaluation pour la date: $dateToEvaluate, prix: ${priceDate.price} €")
            println(simulation.evaluateRSI(selectedPrices))
            println(simulation.evaluateMACD(selectedPrices))
            println(s"Volatilité: ${financialMetrics.volatility().formatted("%.4f")}")
            println(s"Ratio de Sharpe: ${financialMetrics.sharpeRatio().formatted("%.4f")}")

          case None =>
            println(s"Aucun prix trouvé pour la date: $dateToEvaluate")
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