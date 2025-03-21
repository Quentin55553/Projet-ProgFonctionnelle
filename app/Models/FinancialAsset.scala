package Models

import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json._

import java.time.{LocalDateTime, ZoneOffset}
import scala.math.BigDecimal.RoundingMode


case class FinancialAsset (
    symbol: String,
    currentPrice: Double,
    priceChange: Double,
    percentChange: Double,
    highPrice: Double,
    lowPrice: Double,
    openPrice: Double,
    closePrice: Double,
    dateTime: LocalDateTime
)


object FinancialAsset {
    implicit val readJsonAsFinancialAsset: Reads[FinancialAsset] = (
        (JsPath \ "c").read[Double].map(price => BigDecimal(price).setScale(2, RoundingMode.DOWN).toDouble) and
        (JsPath \ "d").read[Double].map(price => BigDecimal(price).setScale(2, RoundingMode.DOWN).toDouble) and
        (JsPath \ "dp").read[Double].map(price => BigDecimal(price).setScale(2, RoundingMode.DOWN).toDouble) and
        (JsPath \ "h").read[Double].map(price => BigDecimal(price).setScale(2, RoundingMode.DOWN).toDouble) and
        (JsPath \ "l").read[Double].map(price => BigDecimal(price).setScale(2, RoundingMode.DOWN).toDouble) and
        (JsPath \ "o").read[Double].map(price => BigDecimal(price).setScale(2, RoundingMode.DOWN).toDouble) and
        (JsPath \ "pc").read[Double].map(price => BigDecimal(price).setScale(2, RoundingMode.DOWN).toDouble) and
        (JsPath \ "t").read[Long].map(timestamp => LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.UTC))

    )((currentPrice, priceChange, percentChange, highPrice, lowPrice, openPrice, closePrice, dateTime) =>
        FinancialAsset("", currentPrice, priceChange, percentChange, highPrice, lowPrice, openPrice, closePrice, dateTime)
    )
    implicit val financialAssetWrites: Writes[FinancialAsset] = Json.writes[FinancialAsset]

}
