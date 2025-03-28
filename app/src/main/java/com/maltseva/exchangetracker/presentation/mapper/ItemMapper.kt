package com.maltseva.exchangetracker.presentation.mapper

import com.maltseva.exchangetracker.domain.model.CurrencyEntity
import com.maltseva.exchangetracker.domain.model.ExchangeRateEntity
import com.maltseva.exchangetracker.presentation.model.CurrencyItem
import com.maltseva.exchangetracker.presentation.model.ExchangeRateItem
import com.maltseva.exchangetracker.presentation.model.SavedCurrencyItem

fun ExchangeRateEntity.toItem(): ExchangeRateItem {
    return ExchangeRateItem(
        currency = this.currency,
        rate = this.rate,
    )
}

fun CurrencyEntity.toItem(isSelected: Boolean): CurrencyItem {
    return CurrencyItem(
        currency = this.currency,
        name = this.name,
        isSelected = isSelected
    )
}

fun SavedCurrencyItem.toCurrencyEntity(): CurrencyEntity {
    return CurrencyEntity(
        currency = this.currency,
        name = this.name
    )
}

fun CurrencyItem.toEntity(): CurrencyEntity {
    return CurrencyEntity(
        currency = this.currency,
        name = this.name
    )
}

fun mapCurrencyToSavedItem(
    currencyEntity: CurrencyItem,
    rates: List<ExchangeRateItem>,
): SavedCurrencyItem {
    val rate = rates.find { it.currency == currencyEntity.currency }?.rate
    return SavedCurrencyItem(
        currency = currencyEntity.currency,
        name = currencyEntity.name,
        rate = rate ?: 0.0
    )
}