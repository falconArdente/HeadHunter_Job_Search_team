package ru.practicum.android.diploma.filter.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filter.domain.api.FilterDictionariesInteractor
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.AreaSuggestion
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.filter.domain.model.Industry
import ru.practicum.android.diploma.utils.Resource

class FilterDictionariesInteractorImpl(private val repository: FilterDictionariesRepository): FilterDictionariesInteractor {

    override suspend fun getAreas(): Flow<Resource<List<Area>>> {
        return repository.getAreas()
    }

    override suspend fun getCountries(): Flow<Resource<List<Country>>> {
        return repository.getCountries()
    }

    override suspend fun getSubAreas(areaId: String): Flow<Resource<List<Area>>> {
        return repository.getSubAreas(areaId)
    }

    override suspend fun getIndustries(): Flow<Resource<List<Industry>>> {
        return repository.getIndustries()
    }

    override suspend fun searchInAreas(searchText: String, areaId: String?): Flow<Resource<List<AreaSuggestion>>> {
        return repository.searchInAreas(searchText, areaId)
    }

}
