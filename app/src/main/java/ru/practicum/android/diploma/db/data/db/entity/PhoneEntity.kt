package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity

@Entity(tableName = "Phone")
data class PhoneEntity(
    val city: String,
    val comment: String?,
    val country: String,
    val formatted: String,
    val number: String,
    val contactsId: String
)
