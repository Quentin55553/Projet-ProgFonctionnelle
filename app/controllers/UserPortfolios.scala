package models

import play.api.libs.json._
import scala.collection.immutable.Map

case class UserPortfolios(userId: String, portfolios: Map[String, Portfolio]) {

  def addPortfolio(portfolio: Portfolio): UserPortfolios = {
    if (portfolios.size >= 3) this
    else copy(portfolios = portfolios + (portfolio.name -> portfolio))
  }

  def updatePortfolio(portfolio: Portfolio): UserPortfolios = {
    copy(portfolios = portfolios + (portfolio.name -> portfolio))
  }
}

object UserPortfolios {
  implicit val format: OFormat[UserPortfolios] = Json.format[UserPortfolios]
}

// Messages Akka pour PortfolioActor
case class AddPortfolio(userId: String, portfolioName: String)
case class AddAssetToPortfolio(userId: String, portfolioName: String, symbol: String, quantity: Int)
case class ModifyAssetInPortfolio(userId: String, portfolioName: String, symbol: String, newQuantity: Int)
case class RemoveAssetFromPortfolio(userId: String, portfolioName: String, symbol: String)
case class GetPortfolios(userId: String)

object PortfolioMessages {
  implicit val addPortfolioFormat: OFormat[AddPortfolio] = Json.format[AddPortfolio]
  implicit val addAssetFormat: OFormat[AddAssetToPortfolio] = Json.format[AddAssetToPortfolio]
  implicit val modifyAssetFormat: OFormat[ModifyAssetInPortfolio] = Json.format[ModifyAssetInPortfolio]
  implicit val removeAssetFormat: OFormat[RemoveAssetFromPortfolio] = Json.format[RemoveAssetFromPortfolio]
  implicit val getPortfoliosFormat: OFormat[GetPortfolios] = Json.format[GetPortfolios]
}
