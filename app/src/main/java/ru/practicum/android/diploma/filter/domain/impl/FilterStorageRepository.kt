package ru.practicum.android.diploma.filter.domain.impl

import ru.practicum.android.diploma.filter.domain.model.Filter

interface FilterStorageRepository {
    fun saveFilter(filter: Filter)

    fun getFilter(): Filter
}
