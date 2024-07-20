package ru.practicum.android.diploma.filter.data.storage

import ru.practicum.android.diploma.filter.domain.model.Filter

interface FilterStorage {
    fun saveFilterParameters(filter: Filter)

    fun getFilterParameters(): Filter

    fun clearAllFilterParameters()

}
