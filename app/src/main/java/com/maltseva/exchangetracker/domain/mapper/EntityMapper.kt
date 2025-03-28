package com.maltseva.exchangetracker.domain.mapper

import com.maltseva.exchangetracker.data.model.CurrencyDto
import com.maltseva.exchangetracker.domain.model.CurrencyEntity

fun CurrencyEntity.toDto(): CurrencyDto {
    return CurrencyDto(
        currency = this.currency,
        name = this.name
    )
}

fun CurrencyDto.toEntity(): CurrencyEntity {
    return CurrencyEntity(
        currency = this.currency,
        name = this.name
    )
}

