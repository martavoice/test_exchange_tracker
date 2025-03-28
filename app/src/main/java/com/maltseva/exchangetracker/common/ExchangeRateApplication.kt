package com.maltseva.exchangetracker.common

import android.app.Application
import com.maltseva.exchangetracker.data.koin.dataBaseModule
import com.maltseva.exchangetracker.data.koin.dataModule
import com.maltseva.exchangetracker.data.koin.networkModule
import com.maltseva.exchangetracker.domain.domainModule
import com.maltseva.exchangetracker.presentation.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ExchangeRateApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ExchangeRateApplication)
            modules(
                networkModule,
                dataBaseModule,
                dataModule,
                domainModule,
                presentationModule
            )
        }
    }
}