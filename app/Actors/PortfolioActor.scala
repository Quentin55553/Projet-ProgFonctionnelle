package Actors

import models._
import akka.actor.{Actor, ActorLogging}

class PortfolioActor extends Actor with ActorLogging {
  var userPortfolios: Map[String, UserPortfolios] = Map.empty

  override def receive: Receive = {
    case AddPortfolio(userId, portfolioName) =>
      val userPortfolio = userPortfolios.getOrElse(userId, UserPortfolios(userId, Map.empty))
      if (userPortfolio.portfolios.size < 3) {
        userPortfolios += userId -> userPortfolio.addPortfolio(Portfolio(portfolioName, Map.empty))
        log.info(s"Portfolio $portfolioName ajouté pour l'utilisateur $userId")
      } else {
        log.warning(s"L'utilisateur $userId a déjà trois portefeuilles")
      }

    case AddAssetToPortfolio(userId, portfolioName, symbol, quantity) =>
      userPortfolios.get(userId).flatMap(_.portfolios.get(portfolioName)) match {
        case Some(portfolio) =>
          val updatedPortfolio = portfolio.addAsset(symbol, quantity)
          userPortfolios += userId -> userPortfolios(userId).updatePortfolio(updatedPortfolio)
          log.info(s"Ajout de $quantity unités de $symbol au portefeuille $portfolioName de l'utilisateur $userId")
        case None => log.warning(s"Portefeuille $portfolioName non trouvé pour l'utilisateur $userId")
      }

    case ModifyAssetInPortfolio(userId, portfolioName, symbol, newQuantity) =>
      userPortfolios.get(userId).flatMap(_.portfolios.get(portfolioName)) match {
        case Some(portfolio) =>
          val updatedPortfolio = portfolio.modifyAsset(symbol, newQuantity)
          userPortfolios += userId -> userPortfolios(userId).updatePortfolio(updatedPortfolio)
          log.info(s"Modification de $symbol dans $portfolioName pour l'utilisateur $userId avec quantité $newQuantity")
        case None => log.warning(s"Portefeuille $portfolioName non trouvé pour l'utilisateur $userId")
      }

    case RemoveAssetFromPortfolio(userId, portfolioName, symbol) =>
      userPortfolios.get(userId).flatMap(_.portfolios.get(portfolioName)) match {
        case Some(portfolio) =>
          val updatedPortfolio = portfolio.removeAsset(symbol)
          userPortfolios += userId -> userPortfolios(userId).updatePortfolio(updatedPortfolio)
          log.info(s"Suppression de $symbol du portefeuille $portfolioName de l'utilisateur $userId")
        case None => log.warning(s"Portefeuille $portfolioName non trouvé pour l'utilisateur $userId")
      }

    case GetPortfolios(userId) =>
      sender() ! userPortfolios.getOrElse(userId, UserPortfolios(userId, Map.empty))
  }
}
