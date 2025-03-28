package com.maltseva.exchangetracker.domain.usecases

import com.maltseva.exchangetracker.data.model.CurrencyDto
import com.maltseva.exchangetracker.data.repository.ExchangeRepository
import com.maltseva.exchangetracker.domain.mapper.toDto
import com.maltseva.exchangetracker.domain.mapper.toEntity
import com.maltseva.exchangetracker.domain.model.CurrencyEntity
import com.maltseva.exchangetracker.domain.model.ExchangeRateEntity

class ExchangeUseCases(
    private val repository: ExchangeRepository
) {
    suspend fun saveNewCurrency(list: List<CurrencyEntity>) {
        repository.saveNewCurrency(list.map { it.toDto() })
    }

    suspend fun deleteSavedCurrency(currencyEntity: CurrencyEntity) {
        repository.deleteSavedCurrency(currencyEntity.toDto())
    }

    suspend fun getSavedCurrencies(): List<CurrencyEntity> {
        return repository.getSavedCurrencies().map { it.toEntity() }
    }

    suspend fun getLatestRates(): List<ExchangeRateEntity> {
        val result = repository.getLatestRates().rates
        return result.map { ExchangeRateEntity(it.key, it.value) }
    }

    suspend fun getAllCurrencies(): List<CurrencyEntity> {
        return repository.getCurrencies().map { it.toEntity() }
    }
}