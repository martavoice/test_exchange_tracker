package com.maltseva.exchangetracker.data.repository

import com.maltseva.exchangetracker.data.model.CurrencyDto
import com.maltseva.exchangetracker.data.model.LatestExchangeRatesDto

interface ExchangeRepository {
    suspend fun getSavedCurrencies(): List<CurrencyDto>
    suspend fun saveNewCurrency(list: List<CurrencyDto>)
    suspend fun deleteSavedCurrency(currencyDto: CurrencyDto)
    suspend fun getCurrencies(): List<CurrencyDto>
    suspend fun getLatestRates(): LatestExchangeRatesDto
}