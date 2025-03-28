package com.maltseva.exchangetracker.data.model

import com.google.gson.annotations.SerializedName

data class ExchangeRateDto(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("rate")
    val rate: Double,
)
