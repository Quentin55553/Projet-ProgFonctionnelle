import cats.effect.unsafe.implicits.global

object Main {
  def main(args: Array[String]): Unit = {
    Database.testConnection.unsafeRunSync()

    // Appel de la méthode pour tester l'ajout d'un utilisateur
    Database.testAddUser().unsafeRunSync()
  }
}
