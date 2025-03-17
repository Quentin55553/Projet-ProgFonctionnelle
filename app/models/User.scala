package models

import play.api.libs.json.{Json, OFormat}


case class User(id: Option[Int], username: String, password_hash: String)


object User {
  implicit val format: OFormat[User] = Json.format[User]
}