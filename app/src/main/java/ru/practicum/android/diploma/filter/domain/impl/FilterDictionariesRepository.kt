package ru.practicum.android.diploma.filter.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.AreaSuggestion
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.filter.domain.model.Industry
import ru.practicum.android.diploma.utils.Resource

interface FilterDictionariesRepository {
    suspend fun getAreas(): Flow<Resource<List<Area>>>
    suspend fun getCountries(): Flow<Resource<List<Country>>>
    suspend fun getSubAreas(areaId: String): Flow<Resource<List<Area>>>
    suspend fun searchInAreas(
        searchText: String,
        areaId: String? = null
    ): Flow<Resource<List<AreaSuggestion>>> // searchText.length = 2..3000

    suspend fun getIndustries(): Flow<Resource<List<Industry>>>
}
