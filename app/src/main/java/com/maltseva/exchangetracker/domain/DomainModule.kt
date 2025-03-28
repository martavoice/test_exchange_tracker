package com.maltseva.exchangetracker.domain

import com.maltseva.exchangetracker.domain.usecases.ExchangeUseCases
import org.koin.dsl.module

val domainModule = module {
    single { ExchangeUseCases(get()) }
}