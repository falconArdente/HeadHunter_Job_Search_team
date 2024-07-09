package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ContactsJoinPhone(
    @Embedded
    val contact: ContactsEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "contactId"
    )
    val phoneList: List<PhoneEntity>
)
