package models

import play.api.libs.json._

case class Portfolio(name: String, assets: Map[String, Int]) {

  def addAsset(symbol: String, quantity: Int): Portfolio = {
    copy(assets = assets.updatedWith(symbol) {
      case Some(existingQty) => Some(existingQty + quantity)
      case None => Some(quantity)
    })
  }

  def modifyAsset(symbol: String, newQuantity: Int): Portfolio = {
    copy(assets = assets.updatedWith(symbol) {
      case Some(_) => Some(newQuantity)
      case None => None
    })
  }

  def removeAsset(symbol: String): Portfolio = {
    copy(assets = assets - symbol)
  }
}

object Portfolio {
  implicit val format: OFormat[Portfolio] = Json.format[Portfolio]
}
