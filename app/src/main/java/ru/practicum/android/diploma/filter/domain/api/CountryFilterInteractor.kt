package ru.practicum.android.diploma.filter.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.AreaDetailsFilterItem
import ru.practicum.android.diploma.filter.domain.model.CountryFilter
import ru.practicum.android.diploma.utils.Resource

interface CountryFilterInteractor {
    fun saveCountry(country: AreaDetailsFilterItem)

    fun getAllSavedParameters(): CountryFilter?

    suspend fun getCountries(): Flow<Resource<List<Area>>>

}
