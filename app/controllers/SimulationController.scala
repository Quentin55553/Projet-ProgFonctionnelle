package controllers

import models._
import java.time.LocalDate
import java.nio.file.{Files, Paths}

object SimulationController extends App {

  val fromDate = LocalDate.of(2025, 2, 14)
  val toDate = LocalDate.of(2025, 3, 14)
  val pricesDates = DataFetcher.fetchHistoricalPrices("AAPL", fromDate, toDate)

  val riskFreeRate = 0.01
  val simulation = new Simulation(pricesDates, riskFreeRate)

  val outputPath = Paths.get("views/results.txt")
  Files.createDirectories(outputPath.getParent)

  val historicalPrices = pricesDates.map { priceDate =>
    s"${priceDate.date} : prix = ${priceDate.price} €"
  }.mkString("\n")

  Files.write(outputPath, historicalPrices.getBytes)

  println(s"Résultats écrits dans le fichier : ${outputPath.toAbsolutePath}")

  val lastDate = pricesDates.last.date

  println("Entrez une date pour l'évaluation (au format YYYY-MM-DD) ou tapez 'exit' pour quitter :")
  var inputDate = scala.io.StdIn.readLine()

  while (inputDate.toLowerCase != "exit") {
    try {
      val dateToEvaluate = LocalDate.parse(inputDate)

      if (dateToEvaluate.isAfter(lastDate)) {
        val prices = pricesDates.map(_.price)
        val prevision = new Prevision(prices)

        val futureDays = (dateToEvaluate.toEpochDay - lastDate.toEpochDay).toInt

        val predictedPricesRegression = prevision.predictFuturePricesWithRegression(futureDays)
        val predictedPriceRegression = predictedPricesRegression.last

        val predictedPricesMA = prevision.predictFuturePricesWithMovingAverage(7, futureDays)
        val predictedPriceMA = predictedPricesMA.last

        val predictions = s"""
          Prédictions pour la date: $dateToEvaluate
          1. Prix prédit (Régression Linéaire): $predictedPriceRegression €
          2. Prix prédit (Moyenne Mobile - 7 jours): $predictedPriceMA €
        """

        Files.write(outputPath, predictions.getBytes)
        println(s"Prédictions pour la date $dateToEvaluate écrites dans le fichier.")
      } else {
        simulation.evaluateIndicatorsForDate(dateToEvaluate)
      }
    } catch {
      case e: Exception => println("Date invalide. Veuillez entrer une date correcte au format YYYY-MM-DD.")
    }

    println("\nEntrez une nouvelle date pour l'évaluation (au format YYYY-MM-DD) ou tapez 'exit' pour quitter :")
    inputDate = scala.io.StdIn.readLine()
  }

  println("Merci d'avoir utilisé la simulation. À bientôt !")
}