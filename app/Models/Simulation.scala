package models

import java.time.LocalDate

class Simulation(prices: List[PriceDate], riskFreeRate: Double) {

  def evaluateRSI(prices: List[Double]): String = {
    val indicators = IndicatorsMarket(prices)
    indicators.RSI() match {
      case Some(rsi) =>
        val conclusion = if (rsi < 30) {
          "C'est le moment d'ACHETER (RSI < 30)."
        } else if (rsi > 70) {
          "C'est le moment de VENDRE (RSI > 70)."
        } else {
          "Pas de signal d'achat ou de vente (RSI entre 30 et 70)."
        }
        s"RSI = $rsi : $conclusion"
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
        val conclusion = if (lastMacd > lastSignalLine) {
          "Signal d'ACHAT (MACD > Signal Line)."
        } else if (lastMacd < lastSignalLine) {
          "Signal de VENTE (MACD < Signal Line)."
        } else {
          "Pas de signal d'achat ou de vente (MACD = Signal Line)."
        }
        s"MACD = $lastMacd, Signal Line = $lastSignalLine : $conclusion"
      case _ =>
        "Pas assez de données pour calculer le MACD ou la Signal Line."
    }
  }

  def evaluateIndicatorsForDate(date: LocalDate): Unit = {
    prices.find(_.date.isEqual(date)) match {
      case Some(priceDate) =>
        val selectedPrices = prices.takeWhile(_.date.isBefore(date.plusDays(1))).map(_.price)
        val financialMetrics = FinancialMetrics(selectedPrices, riskFreeRate)

        println(s"\nÉvaluation pour la date: $date, prix: ${priceDate.price} €")
        println(evaluateRSI(selectedPrices))
        println(evaluateMACD(selectedPrices))
        println(s"Volatilité: ${financialMetrics.volatility().formatted("%.4f")}")
        println(s"Ratio de Sharpe: ${financialMetrics.sharpeRatio().formatted("%.4f")}")

      case None =>
        println(s"Aucun prix trouvé pour la date: $date")
    }
  }
}