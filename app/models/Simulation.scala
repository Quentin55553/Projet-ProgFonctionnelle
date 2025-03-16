package models

import java.time.LocalDate

class Simulation(prices: List[PriceDate], riskFreeRate: Double) {

  def findPriceByDate(date: LocalDate): Option[Double] = {
    prices.find(_.date.isEqual(date)).map(_.price)
  }

  def evaluateRSI(prices: List[Double]): String = {
    val indicators = IndicatorsMarket(prices)
    indicators.RSI() match {
      case Some(rsi) =>
        if (rsi < 30) {
          s"RSI = $rsi : C'est le moment d'ACHETER."
        } else if (rsi > 70) {
          s"RSI = $rsi : C'est le moment de VENDRE."
        } else {
          s"RSI = $rsi : Pas de signal d'achat ou de vente."
        }
      case None =>
        "Pas assez de données pour calculer le RSI."
    }
  }

  def evaluateMACD(prices: List[Double]): String = {
    val indicators = IndicatorsMarket(prices)
    (indicators.MACD(), indicators.SignalLine()) match {
      case (Some(macd), Some(signalLine)) if macd.nonEmpty && signalLine.nonEmpty =>
        val lastMacd = macd.last
        val lastSignalLine = signalLine.last
        if (lastMacd > lastSignalLine) {
          s"MACD = $lastMacd : Signal d'ACHAT."
        } else if (lastMacd < lastSignalLine) {
          s"MACD = $lastMacd : Signal de VENTE."
        } else {
          s"MACD = $lastMacd : Pas de signal d'achat ou de vente."
        }
      case _ =>
        "Pas assez de données pour calculer le MACD ou la Signal Line."
    }
  }

  def evaluateIndicatorsForDate(date: LocalDate): Unit = {
    findPriceByDate(date) match {
      case Some(price) =>
        val selectedPrices = prices.takeWhile(_.date.isBefore(date.plusDays(1))).map(_.price)

        val returns = selectedPrices.sliding(2).map { case List(prev, current) =>
          (current - prev) / prev
        }.toList

        val financialMetrics = FinancialMetrics(selectedPrices, riskFreeRate)

        println(s"\nÉvaluation pour la date: $date, prix: $price €")
        println(evaluateRSI(selectedPrices))
        println(evaluateMACD(selectedPrices))

        println(s"Volatilité: ${financialMetrics.volatility().formatted("%.4f")}")
        println(s"Ratio de Sharpe: ${financialMetrics.sharpeRatio().formatted("%.4f")}")

      case None =>
        println(s"Aucun prix trouvé pour la date: $date")
    }
  }
  def simulateTradingForDates(dates: List[LocalDate]): Unit = {
    for (date <- dates) {
      evaluateIndicatorsForDate(date)
    }
  }
}