package ru.practicum.android.diploma.filter.data.storage

import ru.practicum.android.diploma.filter.domain.model.FilterGeneral

interface FilterStorage {
    fun saveFilterParameters(filter: FilterGeneral)

    fun getFilterParameters(): FilterGeneral

    fun clearAllFilterParameters()

}
