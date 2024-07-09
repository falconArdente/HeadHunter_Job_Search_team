package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Logos")
data class LogosEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val size90: String,
    val size240: String,
    val raw: String,
    val employerId: Int
)
