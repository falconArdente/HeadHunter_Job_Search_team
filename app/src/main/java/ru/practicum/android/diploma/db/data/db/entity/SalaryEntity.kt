package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Salary")
data class SalaryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val currency: String?,
    val salaryFrom: Int?,
    val gross: Boolean?,
    val salaryTo: Int?,
    var jobInfoId: Int
)
