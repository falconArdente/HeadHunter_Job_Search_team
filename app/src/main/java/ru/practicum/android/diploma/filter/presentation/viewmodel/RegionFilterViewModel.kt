package ru.practicum.android.diploma.filter.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filter.domain.api.CountryFilterInteractor
import ru.practicum.android.diploma.filter.domain.api.RegionFilterInteractor
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.presentation.state.CountryFilterState
import ru.practicum.android.diploma.utils.Resource

class RegionFilterViewModel(
    private val regionFilterInteractor: RegionFilterInteractor,
    countryFilterInteractor: CountryFilterInteractor,
) : CountryFilterViewModel(countryFilterInteractor) {

    init {
        viewModelScope.launch {
            doSearchNetworkRequest()
        }
    }

    override suspend fun doSearchNetworkRequest() {
        Log.e("CHECK", "${countryFilterInteractor.getAllSavedParameters()}")
        val isCountrySavedInFilterStorage = countryFilterInteractor.getAllSavedParameters() != null
        Log.e("CHECK2", "${countryFilterInteractor.getAllSavedParameters() != null}")
        if (isCountrySavedInFilterStorage) {
            val savedCountryId = countryFilterInteractor.getAllSavedParameters()?.countryId
            val regionFilter = regionFilterInteractor
            Log.e("CHECK3", "$savedCountryId")
            Log.e("CHECK4", "${regionFilterInteractor != null}")
            if (savedCountryId != null) {
                regionFilterInteractor.getSubAreas(savedCountryId).collect {
                    processSearchAreasListRequest(it)
                }
            }
        } else {
            regionFilterInteractor.getAreas().collect {
                processSearchAreasListRequest(it)
            }
        }
    }

    private fun processSearchAreasListRequest(searchResult: Resource<List<Area>>) {
        if (searchResult.data != null) {
            val regionFilterState = CountryFilterState.CountryContent(searchResult.data)
            _stateLiveData.value = regionFilterState
        } else {
            _stateLiveData.value = CountryFilterState.Error(searchResult.message!!)
        }
    }

    fun saveRegionChoiceToFilter(region: Area) {
        regionFilterInteractor.saveRegion(region)
    }
}
