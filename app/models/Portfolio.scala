package models

case class Portfolio(var value: Double) {
  def show(): Unit = {
    println(s"Valeur actuelle du portefeuille: $value €")
  }
}
