package api

import data.{CandleData, FinancialAsset}
import play.api.libs.json._

import scala.util.Random
import scala.io.Source
import scala.collection.mutable.ListBuffer
import java.net.{HttpURLConnection, URI}
import java.time.LocalDateTime
import java.time.{LocalDateTime, ZoneOffset}

// Signification des valeurs retournées par l'API :
// c : Prix actuel (Current price)
// d : Variation du prix par rapport à la veille (Change)
// dp : Variation en pourcentage par rapport à la veille (Change percent)
// h : Prix le plus haut de la journée (High Price)
// l : Prix le plus bas de la journée (Low Price)
// o : Prix d'ouverture de la journée (Open Price)
// pc : Prix de clôture précédent (Previous Close Price)
// t : Timestamp (Date/heure de l'information, en secondes Unix (depuis le 1 janvier 1970))

class APIHandler() {
    private val baseUrl = "https://finnhub.io/api/v1"
    private val apiKey = "cv2vkohr01qk43tvjktgcv2vkohr01qk43tvjku0"
    private val random = new Random(42)

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

    private def generateRandomCryptoData(symbol: String): FinancialAsset = {
        val currentPrice = BigDecimal(1000 + random.nextDouble() * 50000).setScale(2, BigDecimal.RoundingMode.DOWN).toDouble
        val priceChange = BigDecimal(-100 + random.nextDouble() * 200).setScale(2, BigDecimal.RoundingMode.DOWN).toDouble
        val percentChange = BigDecimal(priceChange / currentPrice * 100).setScale(2, BigDecimal.RoundingMode.DOWN).toDouble
        val highPrice = currentPrice + random.nextDouble() * 200
        val lowPrice = currentPrice - random.nextDouble() * 200
        val openPrice = currentPrice - random.nextDouble() * 50
        val closePrice = currentPrice + random.nextDouble() * 50
        val dateTime = LocalDateTime.now()

        FinancialAsset(symbol, currentPrice, priceChange, percentChange, highPrice, lowPrice, openPrice, closePrice, dateTime)
    }

    private def generateRandomForexData(symbol: String): FinancialAsset = {
        val currentPrice = BigDecimal(1 + random.nextDouble() * 1.5).setScale(4, BigDecimal.RoundingMode.DOWN).toDouble
        val priceChange = BigDecimal(-0.01 + random.nextDouble() * 0.02).setScale(4, BigDecimal.RoundingMode.DOWN).toDouble
        val percentChange = BigDecimal(priceChange / currentPrice * 100).setScale(2, BigDecimal.RoundingMode.DOWN).toDouble
        val highPrice = currentPrice + random.nextDouble() * 0.02
        val lowPrice = currentPrice - random.nextDouble() * 0.02
        val openPrice = currentPrice - random.nextDouble() * 0.01
        val closePrice = currentPrice + random.nextDouble() * 0.01
        val dateTime = LocalDateTime.now()

        FinancialAsset(symbol, currentPrice, priceChange, percentChange, highPrice, lowPrice, openPrice, closePrice, dateTime)
    }

    private def execCandleQuery(queryUrl: String): Option[CandleData] = {
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
                        // On parse la réponse JSON en CandleData
                        return Json.parse(response).asOpt[CandleData]

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

    private def fetchHistoricalFromFinnhub(symbol: String, fromEpoch: Long, toEpoch: Long): Option[List[FinancialAsset]] = {
        // Vérifie si c'est une crypto ou une action
        val (endpoint, finalSymbol) =
            if (symbol.startsWith("BINANCE:") || symbol.startsWith("COINBASE:")) {
                // Crypto
                ("crypto/candle", symbol)
            } else {
                // Action
                ("stock/candle", symbol)
            }

        // Construit l'URL
        val queryUrl = s"$baseUrl/$endpoint?symbol=$finalSymbol&resolution=D&from=$fromEpoch&to=$toEpoch&token=$apiKey"

        execCandleQuery(queryUrl) match {
            case Some(candleData) if candleData.s == "ok" && candleData.t.nonEmpty =>
                // On convertit CandleData en List[FinancialAsset]
                val results = candleData.t.indices.map { i =>
                    val timestamp  = candleData.t(i)
                    val dateTime   = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC)

                    val currentPrice  = candleData.c(i)
                    val openPrice     = candleData.o(i)
                    val highPrice     = candleData.h(i)
                    val lowPrice      = candleData.l(i)
                    val closePrice    = candleData.c(i)
                    val priceChange   = closePrice - openPrice
                    val percentChange = if (openPrice != 0) (priceChange / openPrice) * 100 else 0

                    FinancialAsset(
                        symbol,
                        currentPrice,
                        priceChange,
                        percentChange,
                        highPrice,
                        lowPrice,
                        openPrice,
                        closePrice,
                        dateTime
                    )
                }.toList

                Some(results)

            case _ =>
                // s peut être "no_data" ou 403, etc.
                println(s"Pas de données historiques pour $symbol (ou s != ok).")
                None
        }
    }

    private def generateRandomFaHistoricalData(symbol: String, from: LocalDateTime, to: LocalDateTime): List[FinancialAsset] = {
        val data = ListBuffer[FinancialAsset]()

        var currentTime = from
        while (currentTime.isBefore(to)) {
            val currentPrice = BigDecimal(100 + random.nextDouble() * 500).setScale(2, BigDecimal.RoundingMode.DOWN).toDouble
            val priceChange = BigDecimal(-100 + random.nextDouble() * 200).setScale(2, BigDecimal.RoundingMode.DOWN).toDouble
            val percentChange = BigDecimal(priceChange / currentPrice * 100).setScale(2, BigDecimal.RoundingMode.DOWN).toDouble
            val highPrice = currentPrice + random.nextDouble() * 50
            val lowPrice = currentPrice - random.nextDouble() * 50
            val openPrice = currentPrice - random.nextDouble() * 10
            val closePrice = currentPrice + random.nextDouble() * 10

            data.append(FinancialAsset(symbol, currentPrice, priceChange, percentChange, highPrice, lowPrice, openPrice, closePrice, currentTime))

            currentTime = currentTime.plusHours(1)
        }

        data.toList
    }
    def fetchRealCryptoData(symbol: String): Option[FinancialAsset] = {
        // Convertit "BTC" -> "BINANCE:BTCUSDT"
        // Convertit "ETH" -> "BINANCE:ETHUSDT", etc.
        val exchangeSymbol = symbol.toUpperCase match {
            case "BTC" => "BINANCE:BTCUSDT"
            case "ETH" => "BINANCE:ETHUSDT"
            case "BNB" => "BINANCE:BNBUSDT"
            case "XRP" => "BINANCE:XRPUSDT"
            // etc. Ajoutez les cryptos que vous voulez gérer
            case other =>
                println(s"Crypto inconnue : $other")
                return None
        }

        val queryUrl = s"$baseUrl/quote?symbol=$exchangeSymbol&token=$apiKey"
        // On réutilise execQuery comme pour les actions
        execQuery(queryUrl).map(_.copy(symbol = symbol))
    }


    def fetchStockData(stockSymbol: String): Option[FinancialAsset] = {
        val queryUrl = s"$baseUrl/quote?symbol=$stockSymbol&token=$apiKey"
        execQuery(queryUrl).map(_.copy(symbol = stockSymbol))
    }

    def fetchCryptoData(cryptoSymbol: String): Option[FinancialAsset] = {
        val supportedCryptos = List("BTCUSD", "ETHUSD", "BNBUSD", "XRPUSD", "ADAUSD", "SOLUSD", "DOGEUSD", "DOTUSD", "MATICUSD", "LTCUSD")

        if (!supportedCryptos.contains(cryptoSymbol.toUpperCase)) {
            println(s"Crypto non disponible ou inexistante : $cryptoSymbol")
            return None
        }

        println(s"Génération aléatoire des données pour $cryptoSymbol...")
        Some(generateRandomCryptoData(cryptoSymbol.toUpperCase))
    }

    def fetchForexData(forexSymbol: String): Option[FinancialAsset] = {
        val supportedForexPairs = List("EURUSD", "EURGBP", "EURJPY", "EURCHF", "EURRUB", "EURCNY", "EURAUD", "EURCAD", "EURNZD", "USDGBP", "USDJPY", "USDCAD", "USDAUD", "USDCHF", "USDNZD", "USDRUB", "USDCNY", "GBPJPY", "GBPRUB", "GBPCNY", "GBPAUD", "GBPCAD", "GBPCHF", "GBPNZD")

        if (!supportedForexPairs.contains(forexSymbol.toUpperCase)) {
            println(s"Paire de devises non disponible ou inexistante : $forexSymbol")
            return None
        }

        println(s"Génération aléatoire des données pour $forexSymbol...")
        Some(generateRandomForexData(forexSymbol.toUpperCase))
    }

    def fetchFinancialAssetData(assetSymbol: String, from: LocalDateTime, to: LocalDateTime): Option[List[FinancialAsset]] = {
        val finalSymbol = assetSymbol.toUpperCase match {
            case "BTC" => "BINANCE:BTCUSDT"
            case "ETH" => "BINANCE:ETHUSDT"
            // etc.
            case other => other // ex. "AAPL"
        }

        val fromEpoch = from.toEpochSecond(ZoneOffset.UTC)
        val toEpoch   = to.toEpochSecond(ZoneOffset.UTC)

        fetchHistoricalFromFinnhub(finalSymbol, fromEpoch, toEpoch)
    }

}