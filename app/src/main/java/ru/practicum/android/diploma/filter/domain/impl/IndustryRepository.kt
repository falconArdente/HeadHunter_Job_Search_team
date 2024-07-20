package ru.practicum.android.diploma.filter.domain.impl

import ru.practicum.android.diploma.filter.domain.model.IndustryDetailsFilterItem

interface IndustryRepository {
    fun getSphereOfIndustryId(): String

    fun getSphereOfIndustryName(): String

    fun saveSphereOfIndustry(industry: IndustryDetailsFilterItem)

    fun clearSphereOfIndustry()
}
