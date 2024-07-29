package ru.practicum.android.diploma.filter.data.storage

import ru.practicum.android.diploma.filter.domain.model.FilterGeneral

interface FilterStorage {
    fun saveFinalFilterParameters(filter: FilterGeneral)

    fun saveSpecificFilterParameters(filter: FilterGeneral)

    fun getAllFinalFilterParameters(): FilterGeneral

    fun getAllSavedParameter(): FilterGeneral

    fun clearAllFilterParameters()

    fun clearAllSavedParameters()
}
