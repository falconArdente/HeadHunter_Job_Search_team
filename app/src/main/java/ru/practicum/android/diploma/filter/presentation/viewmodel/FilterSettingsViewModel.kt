package ru.practicum.android.diploma.filter.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filter.domain.impl.FilterStorageRepository
import ru.practicum.android.diploma.filter.domain.model.AreaFilter
import ru.practicum.android.diploma.filter.domain.model.CountryFilter
import ru.practicum.android.diploma.filter.domain.model.FilterGeneral
import ru.practicum.android.diploma.filter.domain.model.IndustryFilter
import ru.practicum.android.diploma.filter.presentation.FilterSettingsFragment
import ru.practicum.android.diploma.filter.presentation.state.FilterSettingsState

class FilterSettingsViewModel(
    private val filterStorage: FilterStorageRepository
) : ViewModel() {

    private var jobStorage: Job? = null
    private var jobLoad: Job? = null
    private var savedFilter: FilterGeneral = FilterGeneral()
    private var originalFilter: FilterGeneral = FilterGeneral()
    private val filterState = MutableLiveData<FilterSettingsState>()
    fun getState(): LiveData<FilterSettingsState> = filterState

    fun loadConfiguredFilterSettings() {
        jobLoad?.cancel()
        jobLoad = viewModelScope.launch(Dispatchers.IO) {
            savedFilter = getConfiguredFilterSettings()
            filterState.postValue(FilterSettingsState.Filter(savedFilter))
            delay(FilterSettingsFragment.DELAY_FILTER_LOAD)
            checkFilterExists()
        }
    }

    fun loadSavedFilterSettings(isFirstLoad: Boolean) {
        if (!isFirstLoad) return

        jobStorage?.cancel()

        jobStorage = viewModelScope.launch(Dispatchers.IO) {
            originalFilter = getSavedFilterSettings()
            resetFilter()
        }
    }

    fun saveFilterSettings() {
        jobStorage?.cancel()

        jobStorage = viewModelScope.launch(Dispatchers.IO) {
            filterStorage.saveAllFilterParameters(savedFilter)
            filterState.postValue(FilterSettingsState.SavedFilter())
        }
    }

    fun resetFilter() {
        savedFilter = getSavedFilterSettings()
        savedFilterToConfigured(savedFilter)
    }

    fun resetFilterSettings() {
        jobStorage?.cancel()

        jobStorage = viewModelScope.launch(Dispatchers.IO) {
            filterStorage.clearAllFilterParameters()
            resetFilter()
            filterState.postValue(FilterSettingsState.SavedFilter())
        }
    }

    private fun getConfiguredFilterSettings(): FilterGeneral {
        return filterStorage.getAllSavedParameters()
    }

    private fun getSavedFilterSettings(): FilterGeneral {
        return filterStorage.getAllFilterParameters()
    }

    private fun savedFilterToConfigured(filter: FilterGeneral) {
        filterStorage.saveArea(filter.area)
        filterStorage.saveCountry(filter.country)
        filterStorage.saveIndustry(filter.industry)
        filterStorage.saveHideNoSalaryItems(filter.hideNoSalaryItems)
        if (filter.expectedSalary != null) {
            filterStorage.saveExpectedSalary(filter.expectedSalary)
        } else {
            filterStorage.saveExpectedSalary(String())
        }
    }

    fun changeSalary(newSalary: String) {
        savedFilter = savedFilter.copy(expectedSalary = newSalary)
        checkFilterExists()
    }

    fun changeHideNoSalary(noSalary: Boolean) {
        savedFilter = savedFilter.copy(hideNoSalaryItems = noSalary)
        checkFilterExists()
    }

    fun resetRegion() {
        resetArea()
        resetCountry()
        loadConfiguredFilterSettings()
    }

    private fun resetArea() {
        filterStorage.saveArea(
            AreaFilter()
        )
    }

    private fun resetCountry() {
        filterStorage.saveCountry(
            CountryFilter()
        )
    }

    fun resetIndustry() {
        filterStorage.saveIndustry(
            IndustryFilter()
        )
        loadConfiguredFilterSettings()
    }

    private fun checkFilterExists() {
        val emptyFilter = FilterGeneral()

        val isActiveApply = savedFilter.country?.countryId != originalFilter.country?.countryId
            || savedFilter.area?.areaId != originalFilter.area?.areaId
            || savedFilter.industry?.industryId != originalFilter.industry?.industryId
            || savedFilter.expectedSalary.isNullOrEmpty() != originalFilter.expectedSalary.isNullOrEmpty()
            || savedFilter.hideNoSalaryItems != originalFilter.hideNoSalaryItems

        val isActiveReset = savedFilter.country?.countryId != emptyFilter.country?.countryId
            || savedFilter.area?.areaId != emptyFilter.area?.areaId
            || savedFilter.industry?.industryId != emptyFilter.industry?.industryId
            || savedFilter.expectedSalary.isNullOrEmpty() != emptyFilter.expectedSalary.isNullOrEmpty()
            || savedFilter.hideNoSalaryItems != emptyFilter.hideNoSalaryItems

        filterState.postValue(FilterSettingsState.InterfaceActivate(isActiveApply, isActiveReset))
    }
}
