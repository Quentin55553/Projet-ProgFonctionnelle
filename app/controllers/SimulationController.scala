package controllers

import models._

import java.time.LocalDate
import scala.math.Ordered.orderingToOrdered

object SimulationController extends App {

  // Liste des prix réalistes générés
  val prices = Seq(
    PriceDate(LocalDate.of(2023, 1, 1), 100.0),
    PriceDate(LocalDate.of(2023, 1, 2), 101.5),
    PriceDate(LocalDate.of(2023, 1, 3), 102.0),
    PriceDate(LocalDate.of(2023, 1, 4), 101.8),
    PriceDate(LocalDate.of(2023, 1, 5), 102.5),
    PriceDate(LocalDate.of(2023, 1, 6), 103.2),
    PriceDate(LocalDate.of(2023, 1, 7), 103.8),
    PriceDate(LocalDate.of(2023, 1, 8), 104.2),
    PriceDate(LocalDate.of(2023, 1, 9), 104.5),
    PriceDate(LocalDate.of(2023, 1, 10), 104.0),
    PriceDate(LocalDate.of(2023, 1, 11), 103.5),
    PriceDate(LocalDate.of(2023, 1, 12), 103.0),
    PriceDate(LocalDate.of(2023, 1, 13), 103.6),
    PriceDate(LocalDate.of(2023, 1, 14), 104.3),
    PriceDate(LocalDate.of(2023, 1, 15), 104.9),
    PriceDate(LocalDate.of(2023, 1, 16), 105.2),
    PriceDate(LocalDate.of(2023, 1, 17), 105.5),
    PriceDate(LocalDate.of(2023, 1, 18), 106.0),
    PriceDate(LocalDate.of(2023, 1, 19), 106.3),
    PriceDate(LocalDate.of(2023, 1, 20), 106.7),
    PriceDate(LocalDate.of(2023, 1, 21), 107.0),
    PriceDate(LocalDate.of(2023, 1, 22), 107.2),
    PriceDate(LocalDate.of(2023, 1, 23), 107.5),
    PriceDate(LocalDate.of(2023, 1, 24), 107.9),
    PriceDate(LocalDate.of(2023, 1, 25), 108.1),
    PriceDate(LocalDate.of(2023, 1, 26), 108.5)
  )

  // Calcul des rendements sur les prix
  def calculateReturns(prices: Seq[PriceDate]): Seq[Double] = {
    prices.sliding(2).collect {
      case Seq(prev, curr) => (curr.price - prev.price) / prev.price
    }.toSeq
  }

  // Calcul du RSI sur les rendements
  def calculateRSI(prices: Seq[PriceDate], period: Int = 14): Double = {
    val priceChanges = prices.sliding(2).collect {
      case Seq(prev, curr) => curr.price - prev.price
    }.toSeq

    val gains = priceChanges.filter(_ > 0).sum / period
    val losses = priceChanges.filter(_ < 0).map(Math.abs).sum / period

    val rs = if (losses == 0) Double.MaxValue else gains / losses
    100 - (100 / (1 + rs))
  }

  // Calcul du MACD (exponentielle moyenne mobile à 12 et 26 périodes)
  def calculateMACD(prices: Seq[PriceDate], shortPeriod: Int = 12, longPeriod: Int = 26): Double = {
    val shortEMA = calculateEMA(prices, shortPeriod)
    val longEMA = calculateEMA(prices, longPeriod)
    shortEMA - longEMA
  }

  // Calcul de l'EMA (Exponential Moving Average)
  def calculateEMA(prices: Seq[PriceDate], period: Int): Double = {
    val k = 2.0 / (period + 1)
    val recentPrices = prices.takeRight(period)
    recentPrices.reverse.foldLeft(0.0) { (ema, price) =>
      ema * (1 - k) + price.price * k
    }
  }

  // Demander à l'utilisateur de choisir une date
  println("Entrez une date pour l'évaluation (au format YYYY-MM-DD) ou tapez 'exit' pour quitter :")

  var inputDate = scala.io.StdIn.readLine()

  // Boucle pour permettre à l'utilisateur de choisir une date ou quitter
  while (inputDate.toLowerCase != "exit") {
    try {
      val dateToEvaluate = LocalDate.parse(inputDate)

      // Vérifier que la date choisie existe dans les prix
      if (prices.exists(_.date == dateToEvaluate)) {
        // Filtrer les prix jusqu'à la date sélectionnée
        val selectedPrices = prices.takeWhile(_.date <= dateToEvaluate)

        // Calculer les rendements jusqu'à cette date
        val selectedReturns = calculateReturns(selectedPrices)

        // Créer un objet FinancialAlgorithm avec les rendements jusqu'à la date sélectionnée
        val riskFreeRate = 0.01
        val financialAlgorithm = FinancialAlgorithm(
          assets = 100000,
          liabilities = 1000,
          portfolioReturns = selectedReturns,
          riskFreeRate = riskFreeRate
        )

        // Calculer le RSI et le MACD
        val rsi = calculateRSI(selectedPrices)
        val macd = calculateMACD(selectedPrices)

        // Calculer et afficher les métriques
        println(s"\nÉvaluation pour la date: $dateToEvaluate, prix: ${selectedPrices.last.price} €")
        println(s"RSI = $rsi : ${if (rsi > 70) "C'est le moment de VENDRE." else if (rsi < 30) "C'est le moment d'ACHETER." else "Pas de signal d'achat ou de vente."}")
        println(s"MACD = $macd : ${if (macd > 0) "Signal d'ACHAT." else "Signal de VENTE."}")
        println(s"Volatilité: ${financialAlgorithm.volatility()}")
        println(s"Sharpe Ratio: ${financialAlgorithm.sharpeRatio()}")

      } else {
        println("Date invalide. Aucun prix trouvé pour cette date.")
      }
    } catch {
      case e: Exception => println("Date invalide. Veuillez entrer une date correcte au format YYYY-MM-DD.")
    }

    // Demander une nouvelle date
    println("\nEntrez une nouvelle date pour l'évaluation (au format YYYY-MM-DD) ou tapez 'exit' pour quitter :")
    inputDate = scala.io.StdIn.readLine()
  }

  // Message de fin lorsque l'utilisateur tape "exit"
  println("Merci d'avoir utilisé la simulation. À bientôt !")
}
