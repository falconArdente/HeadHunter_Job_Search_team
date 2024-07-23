package ru.practicum.android.diploma.filter.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filter.domain.api.PlaceToWorkFilterInteractor
import ru.practicum.android.diploma.filter.presentation.state.PlaceToWorkFilterState

class PlaceToWorkFilterViewModel(private val placeToWorkFilterInteractor: PlaceToWorkFilterInteractor) : ViewModel() {
    private val _stateLiveData = MutableLiveData<PlaceToWorkFilterState>()
    val stateLiveData: LiveData<PlaceToWorkFilterState> = _stateLiveData

    fun saveFilterAreaParameters() {
        val currentState = _stateLiveData.value as PlaceToWorkFilterState.AreaFilter
        placeToWorkFilterInteractor.saveCountry(
            countryId = currentState.countryId,
            countryName = currentState.countryName
        )
        placeToWorkFilterInteractor.saveArea(
            areaId = currentState.areaId,
            areaName = currentState.areaName
        )
    }

    fun clearCountry() {
        placeToWorkFilterInteractor.clearCountry()
        updateCurrentFilterAreaParameters()

    }

    fun clearArea() {
        placeToWorkFilterInteractor.clearArea()
        updateCurrentFilterAreaParameters()
    }

    fun getCurrentFilterAreaParameters() {
        val currentCountry = placeToWorkFilterInteractor.getCurrentCountryChoice()
        Log.e("TEST_PLACE", "${currentCountry?.countryName}")
        val currentArea = placeToWorkFilterInteractor.getCurrentAreaChoice()

        viewModelScope.launch {
//            val check = !currentArea?.areaName.isNullOrEmpty() && currentCountry?.countryName.isNullOrEmpty()
//            Log.e("NO","$check")
//            if (!currentArea?.areaName.isNullOrEmpty() && currentCountry?.countryName.isNullOrEmpty()) {
//                val currentCountry: Pair<String, String> =
//                    placeToWorkFilterInteractor.getActualCountryForRegion(currentArea?.areaId!!)
//                Log.e("NO_RESULT", "${currentCountry.second}")
//                _stateLiveData.value = PlaceToWorkFilterState.AreaFilter(
//                    countryId = currentCountry.first,
//                    countryName = currentCountry.second,
//                    areaId = currentArea.areaId,
//                    areaName = currentArea.areaName
//                )
//            } else {
            _stateLiveData.value = PlaceToWorkFilterState.AreaFilter(
                countryId = currentCountry?.countryId,
                countryName = currentCountry?.countryName,
                areaId = currentArea?.areaId,
                areaName = currentArea?.areaName
            )
//            }
        }
    }

    fun updateCurrentFilterAreaParameters() {
        val currentCountry = placeToWorkFilterInteractor.getCurrentCountryChoice()
        val currentArea = placeToWorkFilterInteractor.getCurrentAreaChoice()
        _stateLiveData.value = PlaceToWorkFilterState.AreaFilter(
            countryId = currentCountry?.countryId,
            countryName = currentCountry?.countryName,
            areaId = currentArea?.areaId,
            areaName = currentArea?.areaName
        )
    }
}



