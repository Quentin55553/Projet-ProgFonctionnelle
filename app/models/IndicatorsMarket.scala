package models

case class IndicatorsMarket(prices: Seq[Double]) {

  // Fonction pour calculer le RSI sur 14 jours
  def RSI(): Double = {
    val period = 14
    val gains = (1 until period).map(i => Math.max(prices(i) - prices(i - 1), 0.0))
    val losses = (1 until period).map(i => Math.max(prices(i - 1) - prices(i), 0.0))

    val avgGain = gains.sum / period
    val avgLoss = losses.sum / period

    val rs = if (avgLoss == 0) Double.MaxValue else avgGain / avgLoss
    100 - (100 / (1 + rs))
  }

  // Fonction pour calculer la moyenne mobile exponentielle (EMA)
  def EMA(period: Int): Seq[Double] = {
    val cste = 2 / (period + 1).toDouble
    var ema = Seq(prices.head)
    for (i <- 1 until prices.length) {
      val newEma = (prices(i) - ema.last) * cste + ema.last
      ema = ema :+ newEma
    }
    ema
  }

  // Calcul du MACD avec les EMA sur 9 jours et 26 jours
  def MACD(): Seq[Double] = {
    val emaShort = EMA(9) // EMA courte sur 9 jours
    val emaLong = EMA(26) // EMA longue sur 26 jours

    // Calcul du MACD comme différence entre les deux EMA
    emaShort.zip(emaLong).map { case (short, long) => short - long }
  }

  // Calcul de la signal line qui est une EMA du MACD sur 9 jours
  def SignalLine(): Seq[Double] = {
    val macd = MACD()
    EMA(9) // On calcule une EMA du MACD sur 9 jours
  }
}
