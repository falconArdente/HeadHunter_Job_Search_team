package ru.practicum.android.diploma.filter.data.repository

import ru.practicum.android.diploma.filter.data.storage.FilterStorage
import ru.practicum.android.diploma.filter.domain.impl.FilterStorageRepository
import ru.practicum.android.diploma.filter.domain.model.Filter

class FilterStorageRepositoryImpl(private val filterStorage: FilterStorage) :
    FilterStorageRepository {
    override fun saveFilter(filter: Filter) {
        filterStorage.saveFilter(filter)
    }

    override fun getFilter(): Filter = filterStorage.getFilter()

}
