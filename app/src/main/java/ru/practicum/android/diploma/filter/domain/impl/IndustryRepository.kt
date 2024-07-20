package ru.practicum.android.diploma.filter.domain.impl

import ru.practicum.android.diploma.filter.domain.model.SphereOfIndustry

interface IndustryRepository {
    fun getSphereOfIndustryId(): String

    fun saveSphereOfIndustry(sphereOfIndustry: SphereOfIndustry)

    fun clearSphereOfIndustryId()
}
