package ru.practicum.android.diploma.filter.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filter.domain.api.RegionFilterInteractor
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.AreaFilter
import ru.practicum.android.diploma.filter.domain.model.AreaSuggestion
import ru.practicum.android.diploma.utils.Resource

class RegionFilterInteractorImpl(
    private val filterStorageRepository: FilterStorageRepository,
    private val filterDictionariesRepository: FilterDictionariesRepository,
) : RegionFilterInteractor {
    override fun saveRegion(area: AreaFilter) {
        filterStorageRepository.saveArea(area)
    }

    override fun getAllSavedParameters(): AreaFilter? = filterStorageRepository.getAllSavedParameters().area

    override suspend fun getAreas(): Flow<Resource<List<Area>>> = filterDictionariesRepository.getDetailedAreas()

    override suspend fun getSubAreas(areaId: String): Flow<Resource<List<Area>>> =
        filterDictionariesRepository.getDetailedSubAreas(areaId)

    override suspend fun searchInAreas(searchText: String, areaId: String?): Flow<Resource<List<AreaSuggestion>>> =
        filterDictionariesRepository.searchInAreas(searchText, areaId)
}
