package Models

import play.api.libs.json.{Json, OFormat}


case class User(id: Option[Int], username: String, password_hash: String)


object User {
  implicit val format: OFormat[User] = Json.format[User]
}
/*pour ajouter un utilisateur via le terminal, utilise ça:
Invoke-WebRequest -Uri "http://localhost:9000/register" `
  -Method POST `
  -Headers @{"Content-Type"="application/json"} `
  -Body '{"username": "Ayoub", "password": "1234"}'
*/