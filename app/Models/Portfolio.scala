package models

import play.api.libs.json._

case class Portfolio(userId: String, assets: Map[String, Int]) {
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

  def removeAsset(symbol: String, quantity: Int): Portfolio = {
    copy(assets = assets.updatedWith(symbol) {
      case Some(existingQty) if existingQty > quantity => Some(existingQty - quantity)
      case _ => None
    })
  }
}

object Portfolio {
  implicit val format: OFormat[Portfolio] = Json.format[Portfolio]
}

// Messages Akka pour PortfolioManagerActor
case class AddAsset(userId: String, symbol: String, quantity: Int)
case class RemoveAsset(userId: String, symbol: String, quantity: Int)
case class GetPortfolio(userId: String)

object PortfolioMessages {
  implicit val addAssetFormat: OFormat[AddAsset] = Json.format[AddAsset]
  implicit val removeAssetFormat: OFormat[RemoveAsset] = Json.format[RemoveAsset]
  implicit val getPortfolioFormat: OFormat[GetPortfolio] = Json.format[GetPortfolio]
}