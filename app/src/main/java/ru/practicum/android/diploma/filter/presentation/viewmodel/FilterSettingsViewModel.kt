package ru.practicum.android.diploma.filter.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filter.domain.impl.FilterStorageRepository
import ru.practicum.android.diploma.filter.domain.model.AreaFilter
import ru.practicum.android.diploma.filter.domain.model.CountryFilter
import ru.practicum.android.diploma.filter.domain.model.FilterGeneral
import ru.practicum.android.diploma.filter.domain.model.IndustryFilter
import ru.practicum.android.diploma.filter.presentation.state.FilterSettingsState

class FilterSettingsViewModel(
    private val filterStorage: FilterStorageRepository
) : ViewModel() {

    private var jobStorage: Job? = null
    private var jobLoad: Job? = null
    private var temporaryFilter: FilterGeneral = FilterGeneral()
    private var filterForSearch: FilterGeneral = FilterGeneral()
    private var filterToDisplay: FilterGeneral = FilterGeneral()
    private var salaryToGo = String()
    private val filterState = MutableLiveData<FilterSettingsState>()
    fun getState(): LiveData<FilterSettingsState> = filterState

    fun updateAllFiltersInfo() {
        jobLoad?.cancel()
        jobLoad = viewModelScope.launch(Dispatchers.IO) {
            val asyncTemporaryFilter = async { getConfiguredFilterSettings() }
            val asyncFilterForSearch = async { getSearchFilterSettings() }
            temporaryFilter = asyncTemporaryFilter.await()
            filterForSearch = asyncFilterForSearch.await()
            filterToDisplay = compareBasedFilter(tempFilter = temporaryFilter, searchFilter = filterForSearch)
            checkFilterExists(filterToDisplay)
        }
    }

    private fun compareBasedFilter(tempFilter: FilterGeneral, searchFilter: FilterGeneral): FilterGeneral {
        return FilterGeneral(
            country = tempFilter.country ?: searchFilter.country,
            area = tempFilter.area ?: searchFilter.area,
            industry = tempFilter.industry ?: searchFilter.industry,
            expectedSalary = tempFilter.expectedSalary ?: searchFilter.expectedSalary,
            hideNoSalaryItems = tempFilter.hideNoSalaryItems ?: searchFilter.hideNoSalaryItems
        )
    }

    fun saveFilterSettings() {
        jobStorage?.cancel()

        jobStorage = viewModelScope.launch(Dispatchers.IO) {
            filterStorage.saveAllFilterParameters(filterToDisplay)
            filterStorage.clearAllSavedParameters()
        }
    }

    private fun resetFilter() {
        filterStorage.clearAllFilterParameters()
        filterStorage.clearAllSavedParameters()
    }

    fun resetFilterSettings() {
        jobStorage?.cancel()
        jobStorage = viewModelScope.launch(Dispatchers.IO) {
            resetFilter()
            updateAllFiltersInfo()
        }
    }

    private fun getConfiguredFilterSettings(): FilterGeneral {
        return filterStorage.getAllSavedParameters()
    }

    private fun getSearchFilterSettings(): FilterGeneral {
        return filterStorage.getAllFilterParameters()
    }

    fun changeSalary(newSalary: String) {
        jobLoad?.cancel()
        salaryToGo = newSalary
        viewModelScope.launch(Dispatchers.IO) {
            val delayer = async { filterStorage.saveExpectedSalary(newSalary) }
            delayer.await()
            updateAllFiltersInfo()
        }
    }

    fun changeHideNoSalary(noSalary: Boolean) {
        jobLoad?.cancel()
        viewModelScope.launch(Dispatchers.IO) {
            val delayer = async { filterStorage.saveHideNoSalaryItems(noSalary) }
            delayer.await()
            updateAllFiltersInfo()
        }
    }

    fun resetRegion() {
        resetArea()
        resetCountry()
        updateAllFiltersInfo()
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
        updateAllFiltersInfo()
    }

    private fun checkFilterExists(filterToShow: FilterGeneral?) {
        val isActiveApply = filterToDisplay != filterForSearch
        val isActiveReset = filterToDisplay != FilterGeneral()
        if (filterToShow == null) {
            filterState.postValue(
                (filterState.value as FilterSettingsState.Data).copy(
                    isActiveApply = isActiveApply,
                    isActiveReset = isActiveReset
                )
            )
        } else {
            filterState.postValue(
                FilterSettingsState.Data(
                    filter = filterToShow,
                    isActiveApply = isActiveApply,
                    isActiveReset = isActiveReset
                )
            )
        }
    }
}
