package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "Vacancy")
data class VacancyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val areaId: String?,
    val countryId: String?,
    val positionId: String?,
    val description: String?,
    val experienceId: String?,
    val salaryId: Int,
    val iconPath: String?,
    val vacancyUrl: String
)
