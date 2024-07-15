package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "JobInfo")
data class JobInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val experience: String?,
    val employment: String?
)
