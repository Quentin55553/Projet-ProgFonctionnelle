import java.time.LocalDate
import models.{PriceDate, Simulation, IndicatorsMarket, Portfolio}

object Main extends App {

  // Exemple de séquence de dates et de prix
  val prices = Seq(
    PriceDate(LocalDate.of(2023, 1, 1), 100.0),
    PriceDate(LocalDate.of(2023, 1, 2), 102.0),
    PriceDate(LocalDate.of(2023, 1, 3), 101.5),
    PriceDate(LocalDate.of(2023, 1, 4), 103.0),
    PriceDate(LocalDate.of(2023, 1, 5), 105.0)
  )

  // Création du portefeuille (avec une valeur de départ, par exemple)
  val portfolio = Portfolio(value = 10000.0)

  // Exemple d'indicateurs (à remplacer par des vrais calculs)
  val indicators = new IndicatorsMarket(prices.map(_.price))

  // Création de la simulation
  val simulation = new Simulation(prices, portfolio, indicators)

  // Simulation des décisions d'achat et de vente pour une liste de dates
  val datesToEvaluate = Seq(
    LocalDate.of(2023, 1, 1),
    LocalDate.of(2023, 1, 2),
    LocalDate.of(2023, 1, 3),
    LocalDate.of(2023, 1, 4),
    LocalDate.of(2023, 1, 5)
  )

  simulation.simulateTradingForDates(datesToEvaluate)
}
