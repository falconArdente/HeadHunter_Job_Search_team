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
        when (searchResult) {
            is Resource.Success -> {
                originalListBeforeSearching = searchResult.data!!
                if (originalListBeforeSearching.isNotEmpty()) {
                    _stateLiveDataRegion.value = AreaFilterState.AreaContent(searchResult.data!!)
                } else {
                    _stateLiveDataRegion.value = AreaFilterState.Empty
                }
            }

            is Resource.Error, is Resource.NotFoundError -> {
                _stateLiveDataRegion.value = AreaFilterState.Error(searchResult.message!!)
            }

            is Resource.InternetConnectionError -> {
                _stateLiveDataRegion.value =
                    AreaFilterState.InternetConnectionError(searchResult.message!!)
            }
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
        Log.e("ERROR", "$searchResult")
        when (searchResult) {
            is Resource.Success -> {
                val regionListReceived = searchResult.data
                if (!regionListReceived.isNullOrEmpty()) {
                    _stateLiveDataRegion.value = AreaFilterState.AreaContent(searchResult.data)
                } else {
                    _stateLiveDataRegion.value = AreaFilterState.Empty
                }
            }

            is Resource.Error, is Resource.NotFoundError -> {
                _stateLiveDataRegion.value = AreaFilterState.Error(searchResult.message!!)
            }

            is Resource.InternetConnectionError -> {
                _stateLiveDataRegion.value =
                    AreaFilterState.InternetConnectionError(searchResult.message!!)
            }
        }
    }

    fun saveRegionChoiceToFilter(region: AreaDetailsFilterItem) {
        regionFilterInteractor.saveRegion(AreaFilter(areaId = region.areaId, areaName = region.areaName))
    }

    fun getOriginalListBeforeSearching() {
        _stateLiveDataRegion.value = AreaFilterState.AreaContent(originalListBeforeSearching)
    }
}
