package ru.practicum.android.diploma.filter.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filter.domain.api.CountryFilterInteractor
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.presentation.state.CountryFilterState
import ru.practicum.android.diploma.utils.Resource

open class CountryFilterViewModel(protected val countryFilterInteractor: CountryFilterInteractor) : ViewModel() {
    protected val _stateLiveData = MutableLiveData<CountryFilterState>(CountryFilterState.Loading)
    val stateLiveData: LiveData<CountryFilterState> = _stateLiveData

    init {
        viewModelScope.launch {
            doSearchNetworkRequest()
        }
    }

    protected open suspend fun doSearchNetworkRequest() {
        countryFilterInteractor.getCountries().collect {
            processSearchCountriesListRequest(it)
        }
    }

    protected open fun processSearchCountriesListRequest(searchCountriesResult: Resource<List<Area>>) {
        if (searchCountriesResult.data != null) {
            val countryFilterState = CountryFilterState.CountryContent(searchCountriesResult.data)
            _stateLiveData.value = countryFilterState
        } else {
            _stateLiveData.value = CountryFilterState.Error(searchCountriesResult.message!!)
        }
    }

    fun saveCountryChoiceToFilter(country: Area) {
        countryFilterInteractor.saveCountry(country)
    }
}
