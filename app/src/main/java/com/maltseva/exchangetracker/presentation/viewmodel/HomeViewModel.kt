package com.maltseva.exchangetracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maltseva.exchangetracker.domain.usecases.ExchangeUseCases
import com.maltseva.exchangetracker.presentation.mapper.mapCurrencyToSavedItem
import com.maltseva.exchangetracker.presentation.mapper.toCurrencyEntity
import com.maltseva.exchangetracker.presentation.mapper.toItem
import com.maltseva.exchangetracker.presentation.model.CurrencyItem
import com.maltseva.exchangetracker.presentation.model.ExchangeRateItem
import com.maltseva.exchangetracker.presentation.model.SavedCurrencyItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCases: ExchangeUseCases
) : ViewModel() {
    private val _currencyList = MutableStateFlow<List<CurrencyItem>>(emptyList())
    private val currencyList = _currencyList.asStateFlow()

    private val _usersCurrencyList = MutableStateFlow<List<SavedCurrencyItem>>(emptyList())
    val usersCurrencyList = _usersCurrencyList.asStateFlow()

    fun getSavedCurrencyList() {
        viewModelScope.launch {
            val result = useCases.getSavedCurrencies().map { it.toItem(true) }
            _currencyList.update { result }
        }
    }

    fun deleteCurrencyItem(item: SavedCurrencyItem) {
        viewModelScope.launch {
            useCases.deleteSavedCurrency(item.toCurrencyEntity())
            getSavedCurrencyList()
        }
    }

    fun startUpdates() {
        viewModelScope.launch {
            while (isActive) {
                val latestRates = useCases.getLatestRates().map { it.toItem() }
                updateState(latestRates)
                delay(TIME_INTERVAL)
            }
        }
    }

    private fun updateState(list: List<ExchangeRateItem>) {
        _usersCurrencyList.update {
            currencyList.value.map { mapCurrencyToSavedItem(it, list) }
        }
    }

    companion object {
        const val TIME_INTERVAL = 5000L
    }
}