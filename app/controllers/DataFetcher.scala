package controllers

import api.APIHandler
import java.time.{LocalDate, LocalDateTime}
import models.PriceDate

object DataFetcher {

  /**
   * Récupère les prix historiques pour un actif donné sur une période spécifiée.
   * Ne conserve qu'un seul prix par jour (le premier prix du jour à 00h00).
   *
   * @param assetSymbol Le symbole de l'actif (ex: "AAPL").
   * @param fromDate    La date de début (inclusive).
   * @param toDate      La date de fin (inclusive).
   * @return Une liste de PriceDate pour la période demandée.
   */
  def fetchHistoricalPrices(assetSymbol: String, fromDate: LocalDate, toDate: LocalDate): List[PriceDate] = {
    val apiHandler = new APIHandler()

    val fromDateTime = LocalDateTime.of(fromDate, java.time.LocalTime.MIDNIGHT)
    val toDateTime = LocalDateTime.of(toDate, java.time.LocalTime.MIDNIGHT)

    apiHandler.fetchFinancialAssetData(assetSymbol, fromDateTime, toDateTime) match {
      case Some(historicalData) =>
        historicalData
          .groupBy(_.dateTime.toLocalDate)
          .toList
          .sortBy(_._1)
          .map { case (date, dataForDay) =>
            val firstRecordOfDay = dataForDay.minBy(_.dateTime.toLocalTime)
            PriceDate(date, firstRecordOfDay.closePrice)
          }

      case None =>
        println(s"Aucune donnée disponible pour $assetSymbol entre $fromDate et $toDate.")
        List.empty
    }
  }

  def main(args: Array[String]): Unit = {
    val assetSymbol = "AAPL"
    val fromDate = LocalDate.of(2025, 2, 14)
    val toDate = LocalDate.of(2025, 3, 14)

    val historicalPrices = fetchHistoricalPrices(assetSymbol, fromDate, toDate)

    println(s"Prix historiques pour $assetSymbol du $fromDate au $toDate :")
    historicalPrices.foreach { priceDate =>
      println(s"${priceDate.date} : prix = ${priceDate.price}")
    }
  }
}