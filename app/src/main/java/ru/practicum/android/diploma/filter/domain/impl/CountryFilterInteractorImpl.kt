package ru.practicum.android.diploma.filter.domain.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.filter.domain.api.CountryFilterInteractor
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.CountryFilter
import ru.practicum.android.diploma.utils.Resource

private const val OTHER_REGIONS = "Другие регионы"

class CountryFilterInteractorImpl(
    private val filterStorageRepository: FilterStorageRepository,
    private val filterDictionariesRepository: FilterDictionariesRepository,
) : CountryFilterInteractor {
    override fun saveCountry(country: Area) {
        filterStorageRepository.saveCountry(country)
    }

    override fun getAllSavedParameters(): CountryFilter? {
        val currentSavedFilterParameters = filterStorageRepository.getAllSavedParameters()
        return currentSavedFilterParameters.country
    }

    override suspend fun getCountries(): Flow<Resource<List<Area>>> = flow {

        filterDictionariesRepository.getAreas().collect { result ->
            when (result) {
                is Resource.Success -> {
                    val areasWithParentId = result.data!!.filter { area ->
                        area.parentId == null
                    }
                    val otherRegionsItem = areasWithParentId.find { it.name == OTHER_REGIONS }
                    if (otherRegionsItem != null) {
                        val filteredCountriesList = areasWithParentId - otherRegionsItem
                        val updatedCountriesList = filteredCountriesList + otherRegionsItem
                        emit(Resource.Success(updatedCountriesList))
                    } else emit(Resource.Success(areasWithParentId))
                }

                is Resource.Error -> emit(Resource.Error(result.message!!))

                is Resource.InternetConnectionError -> emit(Resource.InternetConnectionError(result.message!!))

                is Resource.NotFoundError -> emit(Resource.NotFoundError(result.message!!))
            }
        }
    }
}
