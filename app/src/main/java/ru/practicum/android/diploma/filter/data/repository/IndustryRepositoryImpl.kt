package ru.practicum.android.diploma.filter.data.repository

import ru.practicum.android.diploma.filter.data.storage.FilterStorage
import ru.practicum.android.diploma.filter.domain.impl.IndustryRepository
import ru.practicum.android.diploma.filter.domain.model.IndustryDetailsFilterItem

class IndustryRepositoryImpl(private val filterStorage: FilterStorage) : IndustryRepository {
    override fun getSphereOfIndustryId(): String {
        val savedFilter = filterStorage.getFilterParameters()
        return savedFilter.industryId ?: ""
    }

    override fun getSphereOfIndustryName(): String {
        val savedFilter = filterStorage.getFilterParameters()
        return savedFilter.industryName ?: ""
    }

    override fun saveSphereOfIndustry(industry: IndustryDetailsFilterItem) {
        val savedFilter = filterStorage.getFilterParameters()
        filterStorage.saveFilterParameters(
            savedFilter.copy(
                industryId = industry.industryId,
                industryName = industry.industryName
            )
        )
    }

    override fun clearSphereOfIndustry() {
        val savedFilter = filterStorage.getFilterParameters()
        filterStorage.saveFilterParameters(savedFilter.copy(industryId = null, industryName = null))
    }
}
