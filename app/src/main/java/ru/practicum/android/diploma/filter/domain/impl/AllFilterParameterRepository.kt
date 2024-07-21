package ru.practicum.android.diploma.filter.domain.impl

import ru.practicum.android.diploma.filter.domain.model.FilterGeneral

interface AllFilterParameterRepository {
    fun saveFilter(filter: FilterGeneral)

    fun getFilter(): FilterGeneral

    fun clearAllFilterParameters()

    fun isFilterActive(): Boolean

}
