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

}
