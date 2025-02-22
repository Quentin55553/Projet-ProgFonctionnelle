package models
import math.sqrt

case class FinancialAlgorithm (
  assets: Double,
  liabilities: Double,
  portfolioReturns: Seq[Double],
  riskFreeRate: Double
)
{

  def NAV() : Double = {
    assets - liabilities
  }

  def volatility(): Double = {
    val averageReturn = portfolioReturns.sum / portfolioReturns.length
    val variance = portfolioReturns.map(r => math.pow(r - averageReturn, 2)).sum / portfolioReturns.length
    sqrt(variance)
  }


  def sharpeRatio(): Double = {
    val vol = volatility()
    val averageReturn = portfolioReturns.sum / portfolioReturns.length
    if (vol != 0) (averageReturn - riskFreeRate) / vol else 0.0
  }


}
