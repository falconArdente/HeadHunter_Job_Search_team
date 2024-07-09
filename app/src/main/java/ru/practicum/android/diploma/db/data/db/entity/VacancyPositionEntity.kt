package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "VacancyPosition")
data class VacancyPositionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)
