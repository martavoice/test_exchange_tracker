package com.maltseva.exchangetracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maltseva.exchangetracker.domain.usecases.ExchangeUseCases
import com.maltseva.exchangetracker.presentation.mapper.toEntity
import com.maltseva.exchangetracker.presentation.mapper.toItem
import com.maltseva.exchangetracker.presentation.model.CurrencyItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddCurrencyViewModel(
    private val useCases: ExchangeUseCases
) : ViewModel() {
    private val _currencyList = MutableStateFlow<List<CurrencyItem>>(emptyList())
    val currencyList = _currencyList.asStateFlow()

    fun getAllCurrencies() {
        viewModelScope.launch {
            val selected = useCases.getSavedCurrencies()
            val result = useCases.getAllCurrencies().map { item ->
                item.toItem(selected.any {
                    item.currency == it.currency
                })
            }
            _currencyList.update { result }
        }
    }

    fun addSelectedCurrency(item: CurrencyItem) {
        _currencyList.update { currencyList.value.map { if (it.currency == item.currency) it.copy(isSelected = true) else it } }
    }

    fun removeSelectedCurrency(item: CurrencyItem) {
        _currencyList.update { currencyList.value.map { if (it.currency == item.currency) it.copy(isSelected = false) else it } }
    }

    fun saveSelected() {
        viewModelScope.launch {
            useCases.saveNewCurrency(currencyList.value.filter { it.isSelected }.map { it.toEntity() })
        }
    }

}