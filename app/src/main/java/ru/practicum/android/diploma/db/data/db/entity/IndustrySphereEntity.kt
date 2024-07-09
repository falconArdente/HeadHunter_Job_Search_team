package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "IndustrySphere")
data class IndustrySphereEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val industryId: String
)
