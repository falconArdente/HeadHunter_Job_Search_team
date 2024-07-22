package ru.practicum.android.diploma.filter.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filter.domain.api.CountryFilterInteractor
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.AreaDetailsFilterItem
import ru.practicum.android.diploma.filter.domain.model.CountryFilter
import ru.practicum.android.diploma.utils.Resource

class CountryFilterInteractorImpl(
    private val filterStorageRepository: FilterStorageRepository,
    private val filterDictionariesRepository: FilterDictionariesRepository,
) : CountryFilterInteractor {
    override fun saveCountry(country: AreaDetailsFilterItem) {
        filterStorageRepository.saveCountry(country)
    }

    override fun getAllSavedParameters(): CountryFilter? {
        val currentSavedFilterParameters = filterStorageRepository.getAllSavedParameters()
        return currentSavedFilterParameters.country
    }

    override suspend fun getCountries(): Flow<Resource<List<Area>>> = filterDictionariesRepository.getCountriesByAreas()
}
