package com.maltseva.exchangetracker.data.model

import com.google.gson.annotations.SerializedName

data class LatestExchangeRatesDto(
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("base")
    val base: String,
    @SerializedName("rates")
    val rates: Map<String, Double>
)
