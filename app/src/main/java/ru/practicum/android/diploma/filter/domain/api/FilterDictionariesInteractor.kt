package ru.practicum.android.diploma.filter.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.AreaSuggestion
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.filter.domain.model.Industry
import ru.practicum.android.diploma.utils.Resource

interface FilterDictionariesInteractor {
    suspend fun getAreas(): Flow<Resource<List<Area>>>

    suspend fun getCountries(): Flow<Resource<List<Country>>>

    suspend fun getSubAreas(areaId: String): Flow<Resource<List<Area>>>

    suspend fun searchInAreas(
        searchText: String,
        areaId: String? = null
    ): Flow<Resource<List<AreaSuggestion>>>

    suspend fun getIndustries(): Flow<Resource<List<Industry>>>
}
