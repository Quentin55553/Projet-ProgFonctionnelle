package models

case class Portfolio(var value: Double) {
  var cash = value // Valeur initiale du portefeuille en espèces
  var stock = 0.0   // Actions détenues par le portefeuille

  def show(): Unit = {
    println(s"Valeur actuelle du portefeuille : $cash € et $stock actions.")
  }

  // Fonction d'achat d'actions
  def buy(price: Double, amount: Int): Unit = {
    val totalCost = price * amount
    if (cash >= totalCost) {
      stock += amount
      cash -= totalCost
      println(s"Achat de $amount actions à $price €. Total dépensé : $totalCost €")
    } else {
      println("Fonds insuffisants pour acheter.")
    }
  }

  // Fonction de vente d'actions
  def sell(price: Double, amount: Int): Unit = {
    if (stock >= amount) {
      stock -= amount
      cash += price * amount
      println(s"Vente de $amount actions à $price €. Total reçu : ${price * amount} €")
    } else {
      println("Pas assez d'actions à vendre.")
    }
  }
}
