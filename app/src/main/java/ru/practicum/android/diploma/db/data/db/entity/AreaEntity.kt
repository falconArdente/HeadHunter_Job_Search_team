package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Area")
data class AreaEntity(
    @PrimaryKey
    val id: String,
    val employerId: Long,
    val name: String
)
