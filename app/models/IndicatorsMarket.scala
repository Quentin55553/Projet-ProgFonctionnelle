package models

case class IndicatorsMarket(prices: List[Double]) {

  def RSI(period: Int = 14): Double = {
    var changes = List[Double]()
    for (i <- 1 until prices.length) {
      changes = changes :+ (prices(i) - prices(i - 1))
    }

    var gains = List[Double]()
    var losses = List[Double]()
    for (change <- changes) {
      if (change > 0) {
        gains = gains :+ change
        losses = losses :+ 0.0
      } else {
        gains = gains :+ 0.0
        losses = losses :+ -change
      }
    }

    val avgGain = gains.take(period).sum / period
    val avgLoss = losses.take(period).sum / period

    val rs = if (avgLoss == 0) Double.PositiveInfinity else avgGain / avgLoss

    100 - (100 / (1 + rs))
  }

  def EMA(period: Int): List[Double] = {
    val smoothingFactor = 2.0 / (period + 1)
    var ema = List(prices.head)
    for (i <- 1 until prices.length) {
      val newEma = (prices(i) - ema.last) * smoothingFactor + ema.last
      ema = ema :+ newEma
    }
    ema.tail
  }

  def MACD(): List[Double] = {
    val ema12 = EMA(12)
    val ema26 = EMA(26)
    var macd = List[Double]()
    for (i <- ema12.indices) {
      macd = macd :+ (ema12(i) - ema26(i))
    }
    macd
  }

  def SignalLine(): List[Double] = {
    val macd = MACD()
    val smoothingFactor = 2.0 / (9 + 1)
    var signal = List(macd.head)
    for (i <- 1 until macd.length) {
      val newSignal = (macd(i) - signal.last) * smoothingFactor + signal.last
      signal = signal :+ newSignal
    }
    signal.tail
  }

  def evaluateRSI(): String = {
    val rsi = RSI()
    if (rsi > 70) {
      s"RSI = $rsi : Signal de VENTE (RSI supérieur à 70)."
    } else if (rsi < 30) {
      s"RSI = $rsi : Signal d'ACHAT (RSI inférieur à 30)."
    } else {
      s"RSI = $rsi : Signal incertain (RSI entre 30 et 70)."
    }
  }

  def evaluateMACD(): String = {
    val macd = MACD().last
    val signalLine = SignalLine().last
    val difference = macd - signalLine

    if (difference > 0) {
      s"MACD = $macd, Signal Line = $signalLine : Signal d'ACHAT (MACD croise au-dessus de la Signal Line)."
    } else if (difference < 0) {
      s"MACD = $macd, Signal Line = $signalLine : Signal de VENTE (MACD croise en-dessous de la Signal Line)."
    } else {
      s"MACD = $macd, Signal Line = $signalLine : Signal incertain (les lignes MACD et Signal Line sont proches)."
    }
  }
}