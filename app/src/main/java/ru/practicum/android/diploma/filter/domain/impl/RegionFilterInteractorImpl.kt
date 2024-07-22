package ru.practicum.android.diploma.filter.domain.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.filter.domain.api.RegionFilterInteractor
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.AreaFilter
import ru.practicum.android.diploma.filter.domain.model.AreaSuggestion
import ru.practicum.android.diploma.utils.Resource

class RegionFilterInteractorImpl(
    private val filterStorageRepository: FilterStorageRepository,
    private val filterDictionariesRepository: FilterDictionariesRepository,
) : RegionFilterInteractor {
    override fun saveRegion(area: Area) {
        filterStorageRepository.saveArea(area)
    }

    override fun getAllSavedParameters(): AreaFilter? {
        val currentSavedFilterParameters = filterStorageRepository.getAllSavedParameters()
        return currentSavedFilterParameters.area
    }

    override suspend fun getAreas(): Flow<Resource<List<Area>>> = flow {

        filterDictionariesRepository.getAreas().collect { result ->
            when (result) {
                is Resource.Success -> {
                    val areasWithoutParentId = result.data!!.filter { area ->
                        area.parentId != null
                    }
                    val sortedAreaList = areasWithoutParentId.sortedBy { it.name }
                    emit(Resource.Success(sortedAreaList))
                }

                is Resource.Error -> emit(Resource.Error(result.message!!))

                is Resource.InternetConnectionError -> emit(Resource.InternetConnectionError(result.message!!))

                is Resource.NotFoundError -> emit(Resource.NotFoundError(result.message!!))
            }
        }
    }

    override suspend fun getSubAreas(areaId: String): Flow<Resource<List<Area>>> =
        filterDictionariesRepository.getSubAreas(areaId)

    override suspend fun searchInAreas(searchText: String, areaId: String?): Flow<Resource<List<AreaSuggestion>>> =
        filterDictionariesRepository.searchInAreas(searchText, areaId)
}
