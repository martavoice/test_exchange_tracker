package com.maltseva.exchangetracker.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maltseva.exchangetracker.data.database.model.CurrencyRoomDto

@Dao
interface SavedCurrenciesDao {
    @Query("SELECT * FROM saved_currencies")
    suspend fun getSavedCurrencies(): List<CurrencyRoomDto>

    @Delete
    suspend fun deleteSavedCurrency(currencyRoomDto: CurrencyRoomDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNewCurrency(list: List<CurrencyRoomDto>): List<Long>
}