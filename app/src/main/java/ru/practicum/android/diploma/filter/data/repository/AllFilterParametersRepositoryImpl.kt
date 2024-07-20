package ru.practicum.android.diploma.filter.data.repository

import ru.practicum.android.diploma.filter.data.storage.FilterStorage
import ru.practicum.android.diploma.filter.domain.impl.AllFilterParameterRepository
import ru.practicum.android.diploma.filter.domain.model.Filter

class AllFilterParametersRepositoryImpl(private val filterStorage: FilterStorage) : AllFilterParameterRepository {
    override fun saveFilter(filter: Filter) {
        filterStorage.saveFilterParameters(filter)
    }

    override fun getFilter(): Filter = filterStorage.getFilterParameters()

    override fun clearAllFilterParameters() {
        filterStorage.clearAllFilterParameters()
    }

    override fun isFilterActive(): Boolean {
        val filter = filterStorage.getFilterParameters()
        return (filter.countryId != null || filter.areaId != null || filter.industryId != null
            || filter.expectedSalary != null || filter.hideNoSalaryItems)
    }
}
