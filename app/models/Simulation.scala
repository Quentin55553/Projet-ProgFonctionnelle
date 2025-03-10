package models

import java.time.LocalDate

class Simulation(prices: List[PriceDate], portfolio: Portfolio, indicators: IndicatorsMarket) {

  def findPriceByDate(date: LocalDate): Option[Double] = {
    for (priceDate <- prices) {
      if (priceDate.date.isEqual(date)) {
        return Some(priceDate.price)
      }
    }
    None
  }

  def evaluateIndicatorsForDate(date: LocalDate): Unit = {
    val maybePrice = findPriceByDate(date)
    maybePrice match {
      case Some(price) =>
        portfolio.updateValue(price)

        val rsi = indicators.RSI()
        val macd = indicators.MACD()
        val signalLine = indicators.SignalLine()

        println(s"Évaluation pour la date: $date, prix: $price €")

        if (rsi < 30) {
          println(s"RSI = $rsi : C'est le moment d'ACHETER.")
        } else if (rsi > 70) {
          println(s"RSI = $rsi : C'est le moment de VENDRE.")
        } else {
          println(s"RSI = $rsi : Pas de signal d'achat ou de vente.")
        }

        if (macd.last > signalLine.last) {
          println(s"MACD = ${macd.last} : Signal d'ACHAT.")
        } else if (macd.last < signalLine.last) {
          println(s"MACD = ${macd.last} : Signal de VENTE.")
        } else {
          println(s"MACD = ${macd.last} : Pas de signal d'achat ou de vente.")
        }

        val financialAlgorithm = new FinancialAlgorithm(
          assets = portfolio.value,
          liabilities = 0.0,
          portfolioReturns = portfolio.getReturns,
          riskFreeRate = 0.01
        )

        println(s"Volatilité: ${financialAlgorithm.volatility()}")
        println(s"Sharpe Ratio: ${financialAlgorithm.sharpeRatio()}")

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