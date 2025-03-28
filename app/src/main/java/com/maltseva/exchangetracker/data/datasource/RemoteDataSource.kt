package com.maltseva.exchangetracker.data.datasource

import com.maltseva.exchangetracker.data.model.CurrencyDto
import com.maltseva.exchangetracker.data.model.LatestExchangeRatesDto

interface RemoteDataSource {
   suspend fun getCurrenciesList(): List<CurrencyDto>
   suspend fun getLatestRates(): LatestExchangeRatesDto
}