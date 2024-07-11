package ru.practicum.android.diploma.filter.domain.impl

import ru.practicum.android.diploma.filter.domain.api.FilterInteractor
import ru.practicum.android.diploma.filter.domain.api.FilterStorageRepository
import ru.practicum.android.diploma.filter.domain.model.Filter

class FilterInteractorImpl(private val filterStorageRepository: FilterStorageRepository) : FilterInteractor {
    override fun saveFilter(filter: Filter) {
        filterStorageRepository.saveFilter(filter)
    }

    override fun getFilter(): Filter = filterStorageRepository.getFilter()
}
