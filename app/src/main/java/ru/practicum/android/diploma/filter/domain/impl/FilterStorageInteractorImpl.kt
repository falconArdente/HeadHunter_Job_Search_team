package ru.practicum.android.diploma.filter.domain.impl

import ru.practicum.android.diploma.filter.domain.api.FilterStorageInteractor
import ru.practicum.android.diploma.filter.domain.model.Filter

class FilterStorageInteractorImpl(private val filterStorageRepository: FilterStorageRepository) :
    FilterStorageInteractor {
    override fun saveFilter(filter: Filter) {
        filterStorageRepository.saveFilter(filter)
    }

    override fun getFilter(): Filter = filterStorageRepository.getFilter()
}
