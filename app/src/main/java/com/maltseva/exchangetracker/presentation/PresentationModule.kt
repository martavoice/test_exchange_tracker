package com.maltseva.exchangetracker.presentation

import com.maltseva.exchangetracker.presentation.viewmodel.AddCurrencyViewModel
import com.maltseva.exchangetracker.presentation.viewmodel.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel  { HomeViewModel(get()) }
    viewModel  { AddCurrencyViewModel(get()) }
}