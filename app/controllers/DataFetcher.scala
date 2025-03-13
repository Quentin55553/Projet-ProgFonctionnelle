package controllers

import api.APIHandler
import models.PriceDate
import java.time.LocalDate

object DataFetcher  {
  def main(args: Array[String]): Unit = {
    val apiHandler = new APIHandler()

    val toDate = LocalDate.now()
    val fromDate = toDate.minusDays(30)

    val stockSymbol = "AAPL"
    val prices = apiHandler.fetchHistoricalData(stockSymbol, fromDate, toDate).getOrElse {
      println("Impossible de récupérer les données historiques. Utilisation des données par défaut.")
      List(
        PriceDate(LocalDate.of(2023, 1, 1), 100.0),
        PriceDate(LocalDate.of(2023, 1, 2), 95.0),
        PriceDate(LocalDate.of(2023, 1, 3), 90.0)
      )
    }

    println("Données historiques récupérées :")
    prices.foreach { priceDate =>
      println(s"Date : ${priceDate.date}, Prix : ${priceDate.price}")
    }
  }
}