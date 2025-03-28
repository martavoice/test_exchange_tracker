package com.maltseva.exchangetracker.presentation.model

data class SavedCurrencyItem (
    val currency: String,
    val name: String,
    val rate: Double
)