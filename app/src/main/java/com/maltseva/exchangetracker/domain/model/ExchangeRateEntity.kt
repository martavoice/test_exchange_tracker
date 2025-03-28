package com.maltseva.exchangetracker.domain.model

data class ExchangeRateEntity (
    val currency: String,
    val rate: Double,
)