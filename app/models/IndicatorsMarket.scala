package models

case class IndicatorsMarket(prices: List[Double]) {

  def RSI(period: Int = 14): Option[Double] = {
    if (prices.length < period + 1) {
      None
    } else {
      val changes = prices.sliding(2).map { case List(prev, current) => current - prev }.toList

      var gains = List[Double]()
      var losses = List[Double]()
      for (change <- changes.take(period)) {
        if (change > 0) {
          gains = gains :+ change
          losses = losses :+ 0.0
        } else {
          gains = gains :+ 0.0
          losses = losses :+ -change
        }
      }

      var avgGain = gains.sum / period
      var avgLoss = losses.sum / period

      for (i <- period until changes.length) {
        val change = changes(i)
        if (change > 0) {
          avgGain = (avgGain * (period - 1) + change) / period
          avgLoss = (avgLoss * (period - 1)) / period
        } else {
          avgGain = (avgGain * (period - 1)) / period
          avgLoss = (avgLoss * (period - 1) - change) / period
        }
      }

      val rs = if (avgLoss == 0) Double.PositiveInfinity else avgGain / avgLoss
      Some(100 - (100 / (1 + rs)))
    }
  }

  def EMA(period: Int): Option[List[Double]] = {
    if (prices.length < period) {
      None
    } else {
      val smoothingFactor = 2.0 / (period + 1)
      var ema = List(prices.head)
      for (i <- 1 until prices.length) {
        val newEma = (prices(i) - ema.last) * smoothingFactor + ema.last
        ema = ema :+ newEma
      }
      Some(ema.tail)
    }
  }

  def MACD(): Option[List[Double]] = {
    val ema12 = EMA(12)
    val ema26 = EMA(26)
    (ema12, ema26) match {
      case (Some(e12), Some(e26)) if e12.length == e26.length =>
        Some(e12.zip(e26).map { case (a, b) => a - b })
      case _ =>
        None
    }
  }

  def SignalLine(): Option[List[Double]] = {
    MACD() match {
      case Some(macd) if macd.length >= 9 =>
        val smoothingFactor = 2.0 / (9 + 1)
        var signal = List(macd.head)
        for (i <- 1 until macd.length) {
          val newSignal = (macd(i) - signal.last) * smoothingFactor + signal.last
          signal = signal :+ newSignal
        }
        Some(signal.tail)
      case _ =>
        None
    }
  }
}