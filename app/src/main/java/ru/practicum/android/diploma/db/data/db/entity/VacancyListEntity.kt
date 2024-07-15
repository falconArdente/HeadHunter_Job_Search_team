package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class VacancyListEntity (
    @Embedded
    val vacancy: VacancyEntity,

    @Relation(
        parentColumn = "salaryId",
        entityColumn = "id"
    )
    val salaryList: List<SalaryJoinCurrency>,

    @Relation(
        parentColumn = "industryId",
        entityColumn = "id"
    )
    val industryList: List<IndustryJoinSphereEntity>
)
