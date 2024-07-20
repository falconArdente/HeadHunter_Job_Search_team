package ru.practicum.android.diploma.filter.data.repository

import ru.practicum.android.diploma.filter.data.storage.FilterStorage
import ru.practicum.android.diploma.filter.domain.impl.AllFilterParameterRepository
import ru.practicum.android.diploma.filter.domain.model.FilterGeneral

class AllFilterParametersRepositoryImpl(private val filterStorage: FilterStorage) : AllFilterParameterRepository {
    override fun saveFilter(filter: FilterGeneral) {
        filterStorage.saveFilterParameters(filter)
    }

    override fun getFilter(): FilterGeneral = filterStorage.getFilterParameters()

    override fun clearAllFilterParameters() {
        filterStorage.clearAllFilterParameters()
    }

    override fun isFilterActive(): Boolean {
        val filter = filterStorage.getFilterParameters()
        return (filter.country != null || filter.area != null || filter.industry != null
            || filter.expectedSalary != null || filter.hideNoSalaryItems)
    }
}
