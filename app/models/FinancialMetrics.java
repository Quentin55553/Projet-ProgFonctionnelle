package models

import math.sqrt

case class FinancialMetrics(prices: List[Double], riskFreeRate: Double) {

    private def calculateReturns(): List[Double] = {
        prices.sliding(2).map { case List(prev, current) => (current - prev) / prev }.toList
    }

    def volatility(): Double = {
        val returns = calculateReturns()
        val averageReturn = returns.sum / returns.length
        val variance = returns.map(r => math.pow(r - averageReturn, 2)).sum / returns.length
        sqrt(variance)
    }

    def sharpeRatio(): Double = {
        val returns = calculateReturns()
        val averageReturn = returns.sum / returns.length
        val vol = volatility()
        if (vol != 0) (averageReturn - riskFreeRate) / vol else 0.0
    }
}