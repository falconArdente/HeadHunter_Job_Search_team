package ru.practicum.android.diploma.filter.domain.impl

import ru.practicum.android.diploma.filter.domain.model.Filter

interface AllFilterParameterRepository {
    fun saveFilter(filter: Filter)

    fun getFilter(): Filter

    fun clearAllFilterParameters()

    fun isFilterActive(): Boolean

}
