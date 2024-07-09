package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class EmployerJoinLogo(
    @Embedded
    val employer: EmployerEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "employerId"
    )
    val logoRow: LogosEntity
)
