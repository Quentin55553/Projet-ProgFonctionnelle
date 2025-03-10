package models

class Prevision(prices: List[Double]) {

  def movingAverage(period: Int): List[Double] = {
    prices.sliding(period).map(window => window.sum / period).toList
  }

  def linearRegression(): (Double, Double) = {
    val n = prices.length
    val x = (1 to n).toList
    val y = prices

    val meanX = x.sum / n
    val meanY = y.sum / n

    val numerator = x.zip(y).map { case (xi, yi) => (xi - meanX) * (yi - meanY) }.sum
    val denominator = x.map(xi => math.pow(xi - meanX, 2)).sum

    val slope = numerator / denominator
    val intercept = meanY - slope * meanX

    (slope, intercept)
  }

  def predictFuturePricesWithRegression(futureDays: Int): List[Double] = {
    val (slope, intercept) = linearRegression()
    val lastDay = prices.length
    (1 to futureDays).map(day => slope * (lastDay + day) + intercept).toList
  }

  def predictFuturePricesWithMovingAverage(period: Int, futureDays: Int): List[Double] = {
    val ma = movingAverage(period)
    val lastMA = ma.last
    (1 to futureDays).map(_ => lastMA).toList
  }
}