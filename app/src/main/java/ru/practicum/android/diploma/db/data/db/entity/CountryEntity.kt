package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity

@Entity
class CountryEntity(
    val id: String,
    val name: String,
    val url: String
)

