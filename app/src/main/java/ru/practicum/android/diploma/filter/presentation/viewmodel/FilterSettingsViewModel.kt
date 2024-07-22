package ru.practicum.android.diploma.filter.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filter.domain.impl.FilterStorageRepository
import ru.practicum.android.diploma.filter.domain.model.FilterGeneral
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
            savedFilter = getConfiguredFilterSettings()
            filterState.postValue(FilterSettingsState.Filter(savedFilter))
        }
    }

    fun loadSavedFilterSettings() {
        jobStorage?.cancel()

        jobStorage = viewModelScope.launch(Dispatchers.IO) {
            if (filterStorage.isFilterActive()) {
                savedFilter = getSavedFilterSettings()
                filterState.postValue(FilterSettingsState.Filter(savedFilter))
            } else {
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

    fun resetFilterSettings() {
        jobStorage?.cancel()

        jobStorage = viewModelScope.launch(Dispatchers.IO) {
            filterStorage.clearAllFilterParameters()
            filterState.postValue(FilterSettingsState.SavedFilter())
        }
    }

    private fun getConfiguredFilterSettings(): FilterGeneral {
        return filterStorage.getAllFilterParameters()
    }

    private fun getSavedFilterSettings(): FilterGeneral {
        return filterStorage.getAllSavedParameters()
    }

    fun changeSalary(newSalary: String) {
        filterStorage.saveExpectedSalary(newSalary)
    }

    fun changeHideNoSalary(noSalary: Boolean) {
        filterStorage.saveHideNoSalaryItems(noSalary)
    }

}
