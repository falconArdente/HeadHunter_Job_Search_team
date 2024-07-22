package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class EmployerJoins(
    @Embedded
    val employer: EmployerEntity,

    @Relation(
        parentColumn = "areaId",
        entityColumn = "id"
    )
    val areaRow: AreaEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "employerId"
    )
    val logoRow: LogosEntity?
)
