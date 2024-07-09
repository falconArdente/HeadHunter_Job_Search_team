package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SalaryEntity(
    @PrimaryKey( autoGenerate = true)
    val id: Int,
    val currency: String,
    val salaryFrom: String,
    val gross: Boolean,
    val salaryTo: Int?
)
