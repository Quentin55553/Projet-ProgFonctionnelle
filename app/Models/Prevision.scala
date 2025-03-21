package models

class Prevision(prices: List[Double]) {

  def movingAverage(period: Int): List[Double] = {
    if (prices.length < period) {
      // Pas assez de points, on renvoie une liste vide
      Nil
    } else {
      prices.sliding(period).map(window => window.sum / period).toList
    }
  }

  def linearRegression(): (Double, Double) = {
    val n = prices.length
    // S'il y a 0 ou 1 point, on ne peut pas faire de régression
    if (n <= 1) {
      val singlePrice = prices.headOption.getOrElse(0.0)
      return (0.0, singlePrice)
    }

    val x = (1 to n).toList
    val y = prices

    val meanX = x.sum.toDouble / n
    val meanY = y.sum.toDouble / n

    val numerator = x.zip(y).map { case (xi, yi) => (xi - meanX) * (yi - meanY) }.sum
    val denominator = x.map(xi => math.pow(xi - meanX, 2)).sum

    // Vérifie si le dénominateur est 0 (variance nulle)
    if (denominator == 0) {
      return (0.0, meanY)
    }

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
    if (ma.isEmpty) {
      val fallbackValue = prices.lastOption.getOrElse(0.0)
      return (1 to futureDays).map(_ => fallbackValue).toList
    }
    val lastMA = ma.last
    (1 to futureDays).map(_ => lastMA).toList
  }
}

//en exécutant le url suivant: http://localhost:9000/simulate?userId=8&portfolioName=Portefeuille%20Crypto&from=2023-01-01&to=2023-03-01
//