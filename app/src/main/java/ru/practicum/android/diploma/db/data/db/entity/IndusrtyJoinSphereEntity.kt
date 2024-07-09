package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation


data class IndusrtyJoinSphereEntity(
    @Embedded
    val indusrty: IndustryEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "industryId"
    )
    val sphereList: List<IndustrySphereEntity>
)
