package ru.practicum.android.diploma.filter.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.CountryFilter
import ru.practicum.android.diploma.utils.Resource

interface CountryFilterInteractor {
    suspend fun saveCountry(country: CountryFilter)

    fun getAllSavedParameters(): CountryFilter?

    suspend fun getCountries(): Flow<Resource<List<Area>>>

}
