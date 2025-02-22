package controllers

import models.FinancialAlgorithm
object FinancialAlgorithmController extends App {


  val assets = 100000
  val liabilities = 1000
  val returns = Seq(0.05, 0.02, 0.04)
  val riskFreeRate = 0.01


  val calculator = FinancialAlgorithm(assets,liabilities,returns, riskFreeRate)

  println(s"Volatilité: ${calculator.volatility()}")
  println(s"Sharpe Ratio: ${calculator.sharpeRatio()}")

}
