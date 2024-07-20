package ru.practicum.android.diploma.filter.data.repository

import ru.practicum.android.diploma.filter.data.storage.FilterStorage
import ru.practicum.android.diploma.filter.domain.impl.IndustryRepository
import ru.practicum.android.diploma.filter.domain.model.SphereOfIndustry

class IndustryRepositoryImpl(private val filterStorage: FilterStorage) : IndustryRepository {
    override fun getSphereOfIndustryId(): String {
        val savedFilter = filterStorage.getFilterParameters()
        return savedFilter.industryId ?: ""
    }

    override fun saveSphereOfIndustry(sphereOfIndustry: SphereOfIndustry) {
        val savedFilter = filterStorage.getFilterParameters()
        filterStorage.saveFilterParameters(savedFilter.copy(industryId = sphereOfIndustry.id))
    }

    override fun clearSphereOfIndustryId() {
        val savedFilter = filterStorage.getFilterParameters()
        filterStorage.saveFilterParameters(savedFilter.copy(industryId = null))
    }
}
