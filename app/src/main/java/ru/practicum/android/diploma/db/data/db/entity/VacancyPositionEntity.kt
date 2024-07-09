package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity

@Entity
data class VacancyPositionEntity(
    val id: String,
    val name: String
)
