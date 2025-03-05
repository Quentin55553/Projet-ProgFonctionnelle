package models

import java.time.LocalDate

object Main extends App {

  // Exemple de séquence de dates et de prix
  val prices = Seq(
    PriceDate(LocalDate.of(2023, 1, 1), 100.0),
    PriceDate(LocalDate.of(2023, 1, 2), 102.0),
    PriceDate(LocalDate.of(2023, 1, 3), 101.5),
    PriceDate(LocalDate.of(2023, 1, 4), 103.0),
    PriceDate(LocalDate.of(2023, 1, 5), 105.0),
    PriceDate(LocalDate.of(2023, 1, 6), 104.5),
    PriceDate(LocalDate.of(2023, 1, 7), 106.0),
    PriceDate(LocalDate.of(2023, 1, 8), 107.0),
    PriceDate(LocalDate.of(2023, 1, 9), 108.0),
    PriceDate(LocalDate.of(2023, 1, 10), 110.0),
    PriceDate(LocalDate.of(2023, 1, 11), 111.0),
    PriceDate(LocalDate.of(2023, 1, 12), 112.0),
    PriceDate(LocalDate.of(2023, 1, 13), 113.0),
    PriceDate(LocalDate.of(2023, 1, 14), 115.0),
    PriceDate(LocalDate.of(2023, 1, 15), 116.0),
    PriceDate(LocalDate.of(2023, 1, 16), 117.0),
    PriceDate(LocalDate.of(2023, 1, 17), 118.0),
    PriceDate(LocalDate.of(2023, 1, 18), 119.0),
    PriceDate(LocalDate.of(2023, 1, 19), 120.0),
    PriceDate(LocalDate.of(2023, 1, 20), 121.0),
    PriceDate(LocalDate.of(2023, 1, 21), 122.0),
    PriceDate(LocalDate.of(2023, 1, 22), 123.0),
    PriceDate(LocalDate.of(2023, 1, 23), 124.0),
    PriceDate(LocalDate.of(2023, 1, 24), 125.0),
    PriceDate(LocalDate.of(2023, 1, 25), 126.0),
    PriceDate(LocalDate.of(2023, 1, 26), 127.0)
  )

  // Création du portefeuille
  val portfolio = Portfolio(value = 10000.0)

  // Création des indicateurs
  val indicators = new IndicatorsMarket(prices.map(_.price))

  // Création de la simulation
  val simulation = new Simulation(prices, portfolio, indicators)

  // Liste des dates pour lesquelles nous allons simuler
  val datesToEvaluate = prices.map(_.date)

  // Simulation des décisions d'achat et de vente pour ces dates
  simulation.simulateTradingForDates(datesToEvaluate)
}
