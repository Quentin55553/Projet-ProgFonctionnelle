package Actors

import akka.actor.{Actor, ActorLogging}
import akka.stream.scaladsl.{Source, Flow, Sink}
import akka.stream.Materializer
import api.APIHandler
import data.FinancialAsset

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

case object StartStreaming

class MarketActor()(implicit ec: ExecutionContext, mat: Materializer) extends Actor with ActorLogging {
  val apiHandler = new APIHandler()
  val symbols = List("AAPL", "TSLA", "GOOGL")

  val source: Source[String, _] = Source(symbols).throttle(1, 2.seconds)

  val flow: Flow[String, Option[FinancialAsset], _] = Flow[String].map(symbol => apiHandler.fetchStockInfos(symbol))

  val sink: Sink[Option[FinancialAsset], _] = Sink.foreach {
    case Some(data) => log.info(s"Received market data: $data")
    case None => log.warning("Failed to fetch market data")
  }

  override def receive: Receive = {
    case StartStreaming =>
      log.info("Starting market data stream...")
      source.via(flow).to(sink).run()
  }
}
