package ru.practicum.android.diploma.filter.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filter.domain.api.CountryFilterInteractor
import ru.practicum.android.diploma.filter.domain.api.PlaceToWorkFilterInteractor
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.AreaFilter
import ru.practicum.android.diploma.filter.domain.model.CountryFilter
import ru.practicum.android.diploma.utils.Resource

class CountryFilterInteractorImpl(
    private val filterStorageRepository: FilterStorageRepository,
    private val filterDictionariesRepository: FilterDictionariesRepository,
    private val placeToWorkFilterInteractor: PlaceToWorkFilterInteractor
) : CountryFilterInteractor {
    override suspend fun saveCountry(country: CountryFilter) {
        filterStorageRepository.saveCountry(country)
        val areaId = filterStorageRepository.getAllSavedParameters().area?.areaId ?: return

        val countryForRegion = placeToWorkFilterInteractor.getCountryForRegion(areaId)
        if (countryForRegion?.countryId != country.countryId) {
            filterStorageRepository.saveArea(AreaFilter(areaId = null, areaName = null))
        }
    }

    override fun getAllSavedParameters(): CountryFilter? = filterStorageRepository.getAllSavedParameters().country

    override suspend fun getCountries(): Flow<Resource<List<Area>>> = filterDictionariesRepository.getCountriesByAreas()
}
