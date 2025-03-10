package models

case class Portfolio(var value: Double) {

  private var previousValue: Double = value
  private var returns: Seq[Double] = Seq()

  def updateValue(newValue: Double): Unit = {
    val dailyReturn = (newValue - previousValue) / previousValue
    returns :+= dailyReturn
    previousValue = newValue
  }

  def getReturns: Seq[Double] = returns

  def show(): Unit = {
    println(s"Valeur actuelle du portefeuille: $value €")
  }
}
