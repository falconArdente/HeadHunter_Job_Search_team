package ru.practicum.android.diploma.filter.domain.api

import ru.practicum.android.diploma.filter.domain.model.Filter

interface FilterStorageInteractor {
    fun saveFilter(filter: Filter)

    fun getFilter(): Filter
}
