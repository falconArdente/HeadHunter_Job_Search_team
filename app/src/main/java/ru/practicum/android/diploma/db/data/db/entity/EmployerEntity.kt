package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity

@Entity(tableName = "Employer")
data class EmployerEntity(
    val id: String?,
    val name: String,
    val url: String?,
    val vacancyUrl: String?,
    val isTrusted: Boolean,
    val vacancyId: Int
)

