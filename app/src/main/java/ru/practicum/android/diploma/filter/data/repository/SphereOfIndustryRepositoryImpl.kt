package ru.practicum.android.diploma.filter.data.repository

import ru.practicum.android.diploma.filter.data.storage.FilterStorage
import ru.practicum.android.diploma.filter.domain.impl.SphereOfIndustryRepository
import ru.practicum.android.diploma.filter.domain.model.SphereOfIndustry

class SphereOfIndustryRepositoryImpl(private val filterStorage: FilterStorage) : SphereOfIndustryRepository {
    override fun getSphereOfIndustryId(): String {
        val savedFilter = filterStorage.getFilterParameters()
        return savedFilter.sphereOfIndustryId ?: ""
    }

    override fun saveSphereOfIndustry(sphereOfIndustry: SphereOfIndustry) {
        val savedFilter = filterStorage.getFilterParameters()
        filterStorage.saveFilterParameters(savedFilter.copy(sphereOfIndustryId = sphereOfIndustry.id))
    }

    override fun clearSphereOfIndustryId() {
        val savedFilter = filterStorage.getFilterParameters()
        filterStorage.saveFilterParameters(savedFilter.copy(sphereOfIndustryId = null))
    }
}
