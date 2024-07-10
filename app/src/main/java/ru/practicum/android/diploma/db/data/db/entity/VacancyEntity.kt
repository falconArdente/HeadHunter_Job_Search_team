package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Vacancy")
data class VacancyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val areaId: Int?,
    val countryId: Int?,
    val positionId: Int?,
    val description: String?,
    val experienceId: Int?,
    val industryId: Int,
    val salaryId: Int,
    val vacancyFuncTitleId: Int,
    val iconPath: String?,
    val vacancyUrl: String
)
