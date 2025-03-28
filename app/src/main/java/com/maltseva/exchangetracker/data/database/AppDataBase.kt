package com.maltseva.exchangetracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maltseva.exchangetracker.data.database.dao.SavedCurrenciesDao
import com.maltseva.exchangetracker.data.database.model.CurrencyRoomDto

@Database(entities = [CurrencyRoomDto::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun savedCurrenciesDao(): SavedCurrenciesDao
}