package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Currency")
data class CurrencyEntity(
    val abbr: String,
    @PrimaryKey
    val code: String,
    val name: String,
    val rate: Float
)
