package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity

@Entity(tableName = "Logos")
data class LogosEntity(
    val size90: String,
    val size240: String,
    val raw: String,
    val employerId: Int
)
