package models

case class Portfolio(var value: Double) {

  // Calculer les rendements quotidiens (comparaison de la valeur du portefeuille d'un jour à l'autre)
  private var previousValue: Double = value
  private var returns: Seq[Double] = Seq()

  // Ajouter un rendement basé sur la nouvelle valeur
  def updateValue(newValue: Double): Unit = {
    val dailyReturn = (newValue - previousValue) / previousValue
    returns :+= dailyReturn
    previousValue = newValue
  }

  // Retourner les rendements calculés
  def getReturns: Seq[Double] = returns

  // Afficher la valeur actuelle du portefeuille
  def show(): Unit = {
    println(s"Valeur actuelle du portefeuille: $value €")
  }
}
