package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Country")
class CountryEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val url: String
)

