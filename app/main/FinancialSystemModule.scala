package main

import Actors._
import akka.actor.ActorSystem
import com.google.inject.AbstractModule
import play.api.libs.concurrent.AkkaGuiceSupport

class FinancialSystemModule extends AbstractModule with AkkaGuiceSupport {
  override def configure(): Unit = {
    val system = ActorSystem("FinancialSystem")
    bindActor[PortfolioActor]("portfolioManager")
  }
}
//essaye d'ajouter des actifs aux utilisateurs pour tester de la façon suivante:
//curl.exe -X POST http://localhost:9000/portfolio/add/user456/AAPL/10
//curl.exe -X POST  http://localhost:9000/portfolio/add/user456/TSLA/5
//curl.exe -X POST http://localhost:9000/portfolio/add/user456/GOOGL/7

//après ouvrir dans l'url :http://localhost:9000/portfolio/user456 pour voir les résultats.