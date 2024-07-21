package ru.practicum.android.diploma.filter.domain.model

open class Industry(
    val id: String,
    val industries: List<SphereOfIndustry>,
    val name: String,
) : IndustryDetailsFilterItem(industryId = id, industryName = name)
