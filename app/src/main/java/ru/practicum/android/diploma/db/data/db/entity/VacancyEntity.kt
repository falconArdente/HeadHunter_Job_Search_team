package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Vacancy")
data class VacancyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val jobInfoId: Long?,
    val employerId: Long,
    val name: String,
    val description: String,
    val vacancyUrl: String,
    val dateAdd: String?
)
