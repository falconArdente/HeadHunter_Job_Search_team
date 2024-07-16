package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Area")
data class AreaEntity(
    @PrimaryKey
    val id: String,
    var employerId:  Int,
    val name: String
)
