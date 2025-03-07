package data


case class FinancialAsset(
    symbol: String,
    currentPrice: Double,
    priceChange: Double,
    percentChange: Double,
    highPrice: Double,
    lowPrice: Double,
    openPrice: Double,
    closePrice: Double,
    timestamp: Long


    // Faire getters seulement
)
