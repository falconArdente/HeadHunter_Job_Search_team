package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SkillsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val jobInfoId: Long,
    val name: String
)
