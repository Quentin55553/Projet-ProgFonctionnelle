package Actors

import akka.actor.{Actor, ActorLogging}
import Models._

class PortfolioActor extends Actor with ActorLogging {
  var portfolios: Map[String, Portfolio] = Map.empty

  override def receive: Receive = {
    case AddAsset(userId, symbol, quantity) =>
      val updatedPortfolio = portfolios
        .getOrElse(userId, Portfolio(userId, Map.empty))
        .addAsset(symbol, quantity)

      portfolios += userId -> updatedPortfolio
      log.info(s"Portfolio updated for $userId: ${updatedPortfolio.assets}")

    case RemoveAsset(userId, symbol, quantity) =>
      val updatedPortfolio = portfolios
        .get(userId)
        .map(_.removeAsset(symbol, quantity))

      updatedPortfolio match {
        case Some(portfolio) =>
          portfolios += userId -> portfolio
          log.info(s"Portfolio updated for $userId: ${portfolio.assets}")
        case None =>
          log.warning(s"No portfolio found for user $userId")
      }

    case GetPortfolio(userId) =>
      sender() ! portfolios.getOrElse(userId, Portfolio(userId, Map.empty))
  }
}
