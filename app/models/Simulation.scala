package models

import java.time.LocalDate

class Simulation(prices: Seq[PriceDate], portfolio: Portfolio, indicators: IndicatorsMarket) {

  // Trouver le prix pour une date donnée
  def findPriceByDate(date: LocalDate): Option[Double] = {
    prices.find(_.date == date).map(_.price)
  }

  // Évaluer les indicateurs pour une date donnée
  def evaluateIndicatorsForDate(date: LocalDate): Unit = {
    val maybePrice = findPriceByDate(date)
    maybePrice match {
      case Some(price) =>
        // Récupérer les indicateurs RSI, MACD et Signal Line
        val rsi = indicators.RSI()
        val macd = indicators.MACD()
        val signalLine = indicators.SignalLine()

        println(s"Évaluation pour la date: $date, prix: $price €")

        // Vérification RSI
        if (rsi < 30) {
          println(s"RSI = $rsi : C'est le moment d'ACHETER.")
        } else if (rsi > 70) {
          println(s"RSI = $rsi : C'est le moment de VENDRE.")
        } else {
          println(s"RSI = $rsi : Pas de signal d'achat ou de vente.")
        }

        // Vérification MACD
        if (macd.last > signalLine.last) {
          println(s"MACD = ${macd.last} : Signal d'ACHAT.")
        } else if (macd.last < signalLine.last) {
          println(s"MACD = ${macd.last} : Signal de VENTE.")
        } else {
          println(s"MACD = ${macd.last} : Pas de signal d'achat ou de vente.")
        }

      case None =>
        println(s"Aucun prix trouvé pour la date: $date")
    }
  }

  // Simuler les décisions d'achat et de vente pour plusieurs dates
  def simulateTradingForDates(dates: Seq[LocalDate]): Unit = {
    for (date <- dates) {
      evaluateIndicatorsForDate(date)
    }
  }
}
