package com.maltseva.exchangetracker.data.koin

import com.maltseva.exchangetracker.data.repository.ExchangeRepository
import com.maltseva.exchangetracker.data.repository.ExchangeRepositoryImpl
import com.maltseva.exchangetracker.data.datasource.RemoteDataSource
import com.maltseva.exchangetracker.data.datasource.RemoteDataSourceImpl
import com.maltseva.exchangetracker.data.datasource.RoomDataSource
import com.maltseva.exchangetracker.data.datasource.RoomDataSourceImpl
import org.koin.dsl.module

val dataModule = module {
    factory<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    factory<RoomDataSource> { RoomDataSourceImpl(get()) }
    factory<ExchangeRepository> { ExchangeRepositoryImpl(get(), get()) }
}