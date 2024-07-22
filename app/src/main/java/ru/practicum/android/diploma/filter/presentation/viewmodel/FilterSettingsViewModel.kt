package ru.practicum.android.diploma.filter.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filter.domain.impl.FilterStorageRepository
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.FilterGeneral
import ru.practicum.android.diploma.filter.domain.model.Industry
import ru.practicum.android.diploma.filter.presentation.state.FilterSettingsState

class FilterSettingsViewModel(
    private val filterStorage: FilterStorageRepository
) : ViewModel() {

    private var jobStorage: Job? = null
    private var savedFilter: FilterGeneral = FilterGeneral()


    private val filterState = MutableLiveData<FilterSettingsState>()
    fun getState(): LiveData<FilterSettingsState> = filterState

    fun loadConfiguredFilterSettings() {
        jobStorage?.cancel()

        jobStorage = viewModelScope.launch(Dispatchers.IO) {
            if (!filterStorage.isFilterActive()) {
                savedFilter = getConfiguredFilterSettings()
                filterState.postValue(FilterSettingsState.Filter(savedFilter))
            }
        }
    }

    fun loadSavedFilterSettings() {
        jobStorage?.cancel()

        jobStorage = viewModelScope.launch(Dispatchers.IO) {
            if (filterStorage.isFilterActive()) {
                savedFilter = getSavedFilterSettings()
                filterState.postValue(FilterSettingsState.Filter(savedFilter))
            } else {
                resetFilter()
                filterState.postValue(FilterSettingsState.Filter(FilterGeneral()))
            }
        }
    }

    fun saveFilterSettings() {
        jobStorage?.cancel()

        jobStorage = viewModelScope.launch(Dispatchers.IO) {
            filterStorage.saveAllFilterParameters(savedFilter)
            filterState.postValue(FilterSettingsState.SavedFilter())
        }
    }

    private fun resetFilter() {
        filterStorage.clearAllFilterParameters()
    }

    fun resetFilterSettings() {
        jobStorage?.cancel()

        jobStorage = viewModelScope.launch(Dispatchers.IO) {
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

    fun changeSalary(newSalary: String) {
        filterStorage.saveExpectedSalary(newSalary)
    }

    fun resetSalary() {
        filterStorage.saveExpectedSalary(String())
    }

    fun changeHideNoSalary(noSalary: Boolean) {
        filterStorage.saveHideNoSalaryItems(noSalary)
    }

    fun resetArea() {
        filterStorage.saveArea(
            Area(
                id = String(),
                subAreas = emptyList(),
                name = String(),
                parentId = String()
            )
        )
        loadConfiguredFilterSettings()
    }

    fun resetIndustry() {
        filterStorage.saveIndustry(
            Industry(
                id = String(),
                industries = emptyList(),
                name = String()
            )
        )
        loadConfiguredFilterSettings()
    }


}
