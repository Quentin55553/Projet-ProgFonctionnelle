package api

import data.FinancialAsset
import java.net.{HttpURLConnection, URI}
import scala.io.Source
import play.api.libs.json._

// Signification des valeurs retournées par l'API :
// c : Prix actuel (Current price)
// d : Variation du prix par rapport à la veille (Change)
// dp : Variation en pourcentage par rapport à la veille (Change percent)
// h : Prix le plus haut de la journée (High Price)
// l : Prix le plus bas de la journée (Low Price)
// o : Prix d'ouverture de la journée (Open Price)
// pc : Prix de clôture précédent (Previous Close Price)
// t : Timestamp (Date/heure de l'information, en secondes Unix (depuis le 1 janvier 1970))

// À faire :
// - Méthode pour récupérer le prix des actions (peut-être faire plusieurs méthodes avec des paramètres différents)
// - Méthode pour récupérer le prix des cryptos (à exécuter car normalement payant)(si c'est bien payant, on génère les valeurs aléatoirement)
// - Méthode pour récupérer le prix des devises
// - Méthodes pour récupérer les mouvements récents les plus importants des marchés

class APIHandler() {
    private val baseUrl = "https://finnhub.io/api/v1"
    private val apiKey = "cv2vkohr01qk43tvjktgcv2vkohr01qk43tvjku0"


    private def openConnection(requestUrl: String): HttpURLConnection = {
        val uri = new URI(requestUrl)
        val url = uri.toURL
        val connection = url.openConnection().asInstanceOf[HttpURLConnection]
        connection.setRequestMethod("GET")
        connection
    }


    private def execQuery(queryUrl: String): Option[FinancialAsset] = {
        var attempts = 0
        val maxAttempts = 5

        while (attempts < maxAttempts) {
            try {
                val connection = openConnection(queryUrl)
                val statusCode = connection.getResponseCode

                statusCode match {
                    case 200 =>
                        val response = Source.fromInputStream(connection.getInputStream).mkString
                        connection.getInputStream.close()
                        return Json.parse(response).asOpt[FinancialAsset]

                    case 429 =>
                        println("Limite de requêtes atteinte. Nouvelle tentative dans 1.5 seconde...")
                        Thread.sleep(1500)

                    case _ =>
                        println(s"Erreur inattendue après avoir effectué la requête. Code : $statusCode")
                        return None
                }

            } catch {
                case e: Exception =>
                    println(s"Erreur lors de la requête : ${e.getMessage}")
                    return None
            }

            attempts += 1
        }

        println(s"L'API semble bloquée, impossible d'effectuer la requête suivante : $queryUrl")
        None
    }


    def fetchStockInfos(stockSymbol: String): Option[FinancialAsset] = {
        val queryUrl = s"$baseUrl/quote?symbol=$stockSymbol&token=$apiKey"
        execQuery(queryUrl).map(_.copy(symbol = stockSymbol))
    }

    /*
    def fetchCryptoInfos(cryptoSymbol: String): Option[FinancialAsset] = {

    }


    def fetchForexInfos(forexSymbol: String): Option[FinancialAsset] = {
        
    }
     */
}
