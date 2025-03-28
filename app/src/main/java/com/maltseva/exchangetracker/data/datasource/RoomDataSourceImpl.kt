package com.maltseva.exchangetracker.data.datasource

import com.maltseva.exchangetracker.data.database.dao.SavedCurrenciesDao
import com.maltseva.exchangetracker.data.database.model.CurrencyRoomDto

class RoomDataSourceImpl(
    private val savedCurrenciesDao: SavedCurrenciesDao,
) : RoomDataSource {
    override suspend fun getSavedCurrencies(): List<CurrencyRoomDto> {
        return savedCurrenciesDao.getSavedCurrencies()
    }

    override suspend fun saveCurrency(list: List<CurrencyRoomDto>) {
        savedCurrenciesDao.saveNewCurrency(list)
    }

    override suspend fun deleteSavedCurrency(currencyDto: CurrencyRoomDto) {
        savedCurrenciesDao.deleteSavedCurrency(currencyDto)
    }

}