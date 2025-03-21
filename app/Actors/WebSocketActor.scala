package Actors

import akka.actor.{Actor, ActorRef}

class WebSocketActor(out: ActorRef) extends Actor {
  override def receive: Receive = {
    case msg: String => out ! s"Received: $msg"
  }
}