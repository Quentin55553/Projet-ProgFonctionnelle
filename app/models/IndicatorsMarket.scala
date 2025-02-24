package models

case class IndicatorsMarket (

  prices: Seq[Double]

) {
  def RSI () : Double = {

    val avgPrices = for (i <- 1 until prices.length) yield prices(i) - prices(i-1)



    val profit = avgPrices.map( avgPrices => if (avgPrices > 0) avgPrices else 0.0)
    val losses = avgPrices.map( avgPrices => if (avgPrices <0) avgPrices else 0.0)


    val rsi = 100 - (100/ (1 + profit.sum/losses.sum))
    rsi
  }

def SMA(period :Int ) : Double = {
  prices.sum/period
}
def EMA(prices : Seq[Double], period : Int): Seq[Double] = {
  var ema = Seq(SMA(period))
  val cste = 2 / (period + 1)

  for (i <- 1 to period) {
    val newEma = (prices(i) - ema.last) * cste + ema.last
    ema = ema :+ newEma
  }
  ema
}


  def MACD () : Seq[Double] = {
    val shortPeriod = 12
    val longPeriod  = 26

    val emaShort = EMA(prices,shortPeriod)
    val emaLong = EMA(prices,longPeriod)

    val macd = for (i <- emaShort.indices) yield emaShort(i) - emaLong(i)
    macd
  }

  def macdIndicator(): Seq[Double] = {
    val period = 9
    val macd = MACD()
    EMA(macd,period)
  }

}
