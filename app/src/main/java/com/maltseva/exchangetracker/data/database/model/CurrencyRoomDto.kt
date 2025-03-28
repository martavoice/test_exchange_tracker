package com.maltseva.exchangetracker.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_currencies")
data class CurrencyRoomDto(
    @PrimaryKey @ColumnInfo(name = "currency") val currency: String,
    @ColumnInfo(name = "name") val name: String,
)
