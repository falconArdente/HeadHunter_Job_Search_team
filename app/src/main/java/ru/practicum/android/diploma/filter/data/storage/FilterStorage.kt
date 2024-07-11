package ru.practicum.android.diploma.filter.data.storage

import ru.practicum.android.diploma.filter.domain.model.Filter

interface FilterStorage {
    fun saveFilter(filter: Filter)

    fun getFilter(): Filter
}
