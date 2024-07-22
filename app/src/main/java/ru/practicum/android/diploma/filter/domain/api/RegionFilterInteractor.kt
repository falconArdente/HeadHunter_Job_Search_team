package ru.practicum.android.diploma.filter.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.AreaFilter
import ru.practicum.android.diploma.filter.domain.model.AreaSuggestion
import ru.practicum.android.diploma.utils.Resource

interface RegionFilterInteractor {
    fun saveRegion(area: Area)

    fun getAllSavedParameters(): AreaFilter?

    suspend fun getAreas(): Flow<Resource<List<Area>>>

    suspend fun getSubAreas(areaId: String): Flow<Resource<List<Area>>>

    suspend fun searchInAreas(
        searchText: String,
        areaId: String? = null,
    ): Flow<Resource<List<AreaSuggestion>>> // searchText.length = 2..3000
}
