package models

case class IndicatorsMarket(prices: Seq[Double]) {

  def RSI(period: Int = 14): Double = {
    val changes = prices.sliding(2).map { case Seq(prev, current) => current - prev }.toList
    val (gains, losses) = changes.map { change =>
      if (change > 0) (change, 0.0) else (0.0, -change)
    }.unzip

    val avgGain = gains.take(period).sum / period
    val avgLoss = losses.take(period).sum / period

    val rs = if (avgLoss == 0) Double.PositiveInfinity else avgGain / avgLoss

    100 - (100 / (1 + rs))
  }

  def EMA(period: Int): Seq[Double] = {
    val smoothingFactor = 2.0 / (period + 1)
    prices.foldLeft(Seq(prices.head)) { (ema, price) =>
      val newEma = (price - ema.last) * smoothingFactor + ema.last
      ema :+ newEma
    }.tail
  }

  def MACD(): Seq[Double] = {
    val ema12 = EMA(12)
    val ema26 = EMA(26)
    ema12.zip(ema26).map { case (short, long) => short - long }
  }

  def SignalLine(): Seq[Double] = {
    val macd = MACD()
    val smoothingFactor = 2.0 / (9 + 1)
    macd.foldLeft(Seq(macd.head)) { (signal, value) =>
      val newSignal = (value - signal.last) * smoothingFactor + signal.last
      signal :+ newSignal
    }.tail
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
