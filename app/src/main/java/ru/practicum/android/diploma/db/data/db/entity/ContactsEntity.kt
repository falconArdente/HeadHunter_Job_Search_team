package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contacts")
data class ContactsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val email: String?,
    val name: String?,
    val vacancyId: Int
)
