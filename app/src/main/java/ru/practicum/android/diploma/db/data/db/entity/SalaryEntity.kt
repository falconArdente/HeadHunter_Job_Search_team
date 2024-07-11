package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Salary")
data class SalaryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val currencyCode: String,
    val salaryFrom: String,
    val gross: Boolean,
    val salaryTo: Int?
)
