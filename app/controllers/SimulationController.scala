package models

import java.time.LocalDate

object SimulationController extends App {

  // Création de l'instance de la classe generatePrices
  val priceGenerator = new generatePrices()

  // Générer des prix fluctuants pour 26 jours à partir du 1er janvier 2023 avec un prix initial de 100
  val prices = priceGenerator.generate(LocalDate.of(2023, 1, 1), 26, 100.0)

  // Création du portefeuille
  val portfolio = Portfolio(value = 10000.0)

  // Création des indicateurs
  val indicators = new IndicatorsMarket(prices.map(_.price))

  // Création de la simulation
  val simulation = new Simulation(prices, portfolio, indicators)

  // Afficher les prix générés (facultatif)
  println("Prix générés :")
  prices.foreach(p => println(s"Date: ${p.date}, Prix: ${p.price}"))

  // Demander à l'utilisateur de choisir une date
  println("\nEntrez une date pour l'évaluation (au format YYYY-MM-DD) :")
  val inputDate = scala.io.StdIn.readLine()

  try {
    val dateToEvaluate = LocalDate.parse(inputDate)
    // Vérifier que la date choisie existe dans les prix
    if (prices.exists(_.date == dateToEvaluate)) {
      // Si la date est valide, on évalue les indicateurs pour cette date
      simulation.evaluateIndicatorsForDate(dateToEvaluate)
    } else {
      println("Date invalide. Aucun prix trouvé pour cette date.")
    }
  } catch {
    case e: Exception => println("Date invalide. Veuillez entrer une date correcte au format YYYY-MM-DD.")
  }
}
