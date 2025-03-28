package com.maltseva.exchangetracker.data.datasource

import com.maltseva.exchangetracker.data.database.model.CurrencyRoomDto

interface RoomDataSource {
    suspend fun getSavedCurrencies(): List<CurrencyRoomDto>
    suspend fun saveCurrency(list: List<CurrencyRoomDto>)
    suspend fun deleteSavedCurrency(currencyDto: CurrencyRoomDto)
}