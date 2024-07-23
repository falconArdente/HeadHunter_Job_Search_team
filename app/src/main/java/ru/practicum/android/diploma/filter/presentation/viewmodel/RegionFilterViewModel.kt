package ru.practicum.android.diploma.filter.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filter.domain.api.CountryFilterInteractor
import ru.practicum.android.diploma.filter.domain.api.RegionFilterInteractor
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.AreaDetailsFilterItem
import ru.practicum.android.diploma.filter.domain.model.AreaFilter
import ru.practicum.android.diploma.filter.domain.model.AreaSuggestion
import ru.practicum.android.diploma.filter.presentation.state.AreaFilterState
import ru.practicum.android.diploma.utils.Resource

class RegionFilterViewModel(
    private val regionFilterInteractor: RegionFilterInteractor,
    private val countryFilterInteractor: CountryFilterInteractor,
) : ViewModel() {

    private val _stateLiveDataRegion = MutableLiveData<AreaFilterState>(AreaFilterState.Loading)
    val stateLiveDataRegion: LiveData<AreaFilterState> = _stateLiveDataRegion

    private var originalListBeforeSearching = emptyList<AreaDetailsFilterItem>()

    init {
        viewModelScope.launch {
            val isCountrySavedInFilterStorage =
                countryFilterInteractor.getAllSavedParameters()?.countryId.isNullOrEmpty()
            if (!isCountrySavedInFilterStorage) {
                val savedCountryId = countryFilterInteractor.getAllSavedParameters()!!.countryId
                if (savedCountryId != null) {
                    regionFilterInteractor.getSubAreas(savedCountryId).collect {
                        processSearchAreasListResponse(it)
                    }
                }
            } else {
                regionFilterInteractor.getAreas().collect {
                    processSearchAreasListResponse(it)
                }
            }
        }
    }

    private fun processSearchAreasListResponse(searchResult: Resource<List<Area>>) {
        if (searchResult.data != null) {
            val regionFilterState = AreaFilterState.AreaContent(searchResult.data)
            originalListBeforeSearching = searchResult.data
            _stateLiveDataRegion.value = regionFilterState
        } else {
            _stateLiveDataRegion.value = AreaFilterState.Error(searchResult.message!!)
        }
    }

    fun searchRegionByName(regionName: String) {
        val savedCountryId = countryFilterInteractor.getAllSavedParameters()?.countryId
        viewModelScope.launch {
            regionFilterInteractor.searchInAreas(searchText = regionName, areaId = savedCountryId).collect {
                processSearchRegionByNameResponse(it)
            }
        }
    }

    private fun processSearchRegionByNameResponse(searchResult: Resource<List<AreaSuggestion>>) {
        if (searchResult.data != null) {
            val regionListReceived = searchResult.data
            if (!regionListReceived.isNullOrEmpty()) {
                _stateLiveDataRegion.value = AreaFilterState.AreaContent(searchResult.data)
            } else _stateLiveDataRegion.value = AreaFilterState.Empty
        } else {
            _stateLiveDataRegion.value = AreaFilterState.Error(searchResult.message!!)
        }
    }

    fun saveRegionChoiceToFilter(region: AreaDetailsFilterItem) {
        Log.e("TEST8.1", "${region.areaName}")
        val savedCountry = countryFilterInteractor.getAllSavedParameters()?.countryName
        Log.e("TEST8", "$savedCountry")
        regionFilterInteractor.saveRegion(AreaFilter(areaId = region.areaId, areaName = region.areaName))
        val savedCountry2 = countryFilterInteractor.getAllSavedParameters()?.countryName
        Log.e("TEST9.1", "$savedCountry2")
        val savedArea = regionFilterInteractor.getAllSavedParameters()?.areaName
        Log.e("TEST9", "$savedArea")
        Log.e("TEST10", "$savedCountry")

    }

    fun getOriginalListBeforeSearching() {
        _stateLiveDataRegion.value = AreaFilterState.AreaContent(originalListBeforeSearching)
    }
}
