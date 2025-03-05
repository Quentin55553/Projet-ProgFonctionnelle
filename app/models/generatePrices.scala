package models

import java.time.LocalDate
import scala.util.Random

class generatePrices {

  // Fonction pour générer des prix fluctuants
  def generate(startDate: LocalDate, numDays: Int, startPrice: Double): Seq[PriceDate] = {
    val random = new Random()
    var prices = Seq[PriceDate]()
    var currentPrice = startPrice

    for (i <- 0 until numDays) {
      // Variation aléatoire entre -2% et +2% pour le prix
      val priceChange = currentPrice * (random.nextDouble() * 0.04 - 0.02) // -2% à +2%
      currentPrice += priceChange
      prices :+= PriceDate(startDate.plusDays(i), currentPrice)
    }

    prices
  }
}
