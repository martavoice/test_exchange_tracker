package com.maltseva.exchangetracker.data

import com.maltseva.exchangetracker.data.model.CurrencyDto
import com.maltseva.exchangetracker.data.model.LatestExchangeRatesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeApi {
    @GET("latest.json")
    suspend fun getLatestRates(@Query("app_id") appId: String): LatestExchangeRatesDto

    @GET("currencies.json")
    suspend fun getCurrenciesList(@Query("app_id") appId: String): Map<String, String>
}