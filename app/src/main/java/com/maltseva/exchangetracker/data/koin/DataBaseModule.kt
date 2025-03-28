package com.maltseva.exchangetracker.data.koin

import android.app.Application
import androidx.room.Room
import com.maltseva.exchangetracker.data.database.AppDataBase
import com.maltseva.exchangetracker.data.database.dao.SavedCurrenciesDao
import org.koin.dsl.module

fun provideDataBase(application: Application): AppDataBase =
    Room.databaseBuilder(
        application,
        AppDataBase::class.java,
        "exchange_rate_database"
    ).fallbackToDestructiveMigration().build()


fun provideSavedCurrenciesDao(dataBase: AppDataBase): SavedCurrenciesDao = dataBase.savedCurrenciesDao()

val dataBaseModule = module {
    single { provideDataBase(get()) }
    single { provideSavedCurrenciesDao(get()) }
}