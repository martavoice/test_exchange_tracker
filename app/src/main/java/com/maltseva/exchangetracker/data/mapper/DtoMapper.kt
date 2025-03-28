package com.maltseva.exchangetracker.data.mapper

import com.maltseva.exchangetracker.data.database.model.CurrencyRoomDto
import com.maltseva.exchangetracker.data.model.CurrencyDto

fun CurrencyDto.toRoomDto(): CurrencyRoomDto {
    return CurrencyRoomDto(
        currency = this.currency,
        name = this.name,
    )
}

fun CurrencyRoomDto.toDto(): CurrencyDto {
    return CurrencyDto(
        currency = this.currency,
        name = this.name
    )
}