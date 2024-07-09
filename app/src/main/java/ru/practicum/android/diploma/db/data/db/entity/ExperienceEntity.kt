package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Expirience")
data class ExperienceEntity(
    @PrimaryKey
    val id: String,
    val name: String
)
