package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Area")
class AreaEntity (
    @PrimaryKey
    val id: String,
    val name: String,
    val prepositional: String?,
    val parent_id: String?
)
