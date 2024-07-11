package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Phone")
data class PhoneEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val city: String,
    val comment: String?,
    val country: String,
    val formatted: String,
    val number: String,
    val contactsId: String
)
