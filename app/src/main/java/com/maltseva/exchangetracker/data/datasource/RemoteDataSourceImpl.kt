package com.maltseva.exchangetracker.data.datasource

import com.maltseva.exchangetracker.data.ExchangeApi
import com.maltseva.exchangetracker.data.model.CurrencyDto
import com.maltseva.exchangetracker.data.model.LatestExchangeRatesDto

const val app_id = "d35b9f1882c649198489a9276c72c107"

class RemoteDataSourceImpl(
    private val apiService: ExchangeApi
) : RemoteDataSource {
    override suspend fun getCurrenciesList(): List<CurrencyDto> {
        return apiService.getCurrenciesList(app_id).map { CurrencyDto(it.key, it.value) }
    }

    override suspend fun getLatestRates(): LatestExchangeRatesDto {
        return apiService.getLatestRates(app_id)
    }
}