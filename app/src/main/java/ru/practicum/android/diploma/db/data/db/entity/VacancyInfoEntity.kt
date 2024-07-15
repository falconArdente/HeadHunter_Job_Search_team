package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class VacancyInfoEntity(
    @Embedded
    val vacancy: VacancyEntity,

    @Relation(
        parentColumn = "areaId",
        entityColumn = "id"
    )
    val area: AreaEntity?,

    @Relation(
        parentColumn = "id",
        entityColumn = "vacancyId"
    )

    val brandSnippet: BrandSnippetEntity?,

    val brandSnippetList: List<BrandSnippetEntity>,


    @Relation(
        parentColumn = "countryId",
        entityColumn = "id"
    )
    val country: CountryEntity?,

    @Relation(
        parentColumn = "id",
        entityColumn = "vacancyId"
    )
    val employer: EmployerJoinLogo?,

    @Relation(
        parentColumn = "experienceId",
        entityColumn = "id"
    )
    val experience: ExperienceEntity?,

    @Relation(
        parentColumn = "industryId",
        entityColumn = "id"
    )
    val industryList: List<IndustryJoinSphereEntity>,

    @Relation(
        parentColumn = "salaryId",
        entityColumn = "id"
    )
    val salary: SalaryJoinCurrency?,

    @Relation(
        parentColumn = "vacancyFuncTitleId",
        entityColumn = "id"
    )
    val positionList: List<VacancyFuncTitleEntity>
)
