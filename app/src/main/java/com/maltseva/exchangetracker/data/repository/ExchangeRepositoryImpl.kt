package com.maltseva.exchangetracker.data.repository

import com.maltseva.exchangetracker.data.datasource.RemoteDataSource
import com.maltseva.exchangetracker.data.datasource.RoomDataSource
import com.maltseva.exchangetracker.data.mapper.toDto
import com.maltseva.exchangetracker.data.mapper.toRoomDto
import com.maltseva.exchangetracker.data.model.CurrencyDto
import com.maltseva.exchangetracker.data.model.LatestExchangeRatesDto

class ExchangeRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val roomDataSource: RoomDataSource,
) : ExchangeRepository {
    override suspend fun getSavedCurrencies(): List<CurrencyDto> {
        return roomDataSource.getSavedCurrencies().map { it.toDto() }
    }

    override suspend fun saveNewCurrency(list: List<CurrencyDto>) {
        try {




        roomDataSource.saveCurrency(list.map { it.toRoomDto() })
      } catch(e: Exception) {
            println("")
      }
    }


    override suspend fun deleteSavedCurrency(currencyDto: CurrencyDto) {
        roomDataSource.deleteSavedCurrency(currencyDto.toRoomDto())
    }

    override suspend fun getCurrencies(): List<CurrencyDto> {
        return remoteDataSource.getCurrenciesList()
    }

    override suspend fun getLatestRates(): LatestExchangeRatesDto {
        return remoteDataSource.getLatestRates()
    }

}