package ru.practicum.android.diploma.filter.domain.api

import ru.practicum.android.diploma.filter.domain.model.Filter

interface FilterInteractor {
    fun saveFilter(filter: Filter)

    fun getFilter(): Filter
}
