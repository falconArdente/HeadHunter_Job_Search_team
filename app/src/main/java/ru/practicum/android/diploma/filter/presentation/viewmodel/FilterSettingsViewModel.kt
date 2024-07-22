package ru.practicum.android.diploma.filter.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.filter.presentation.state.FilterSettingsState

class FilterSettingsViewModel : ViewModel() {

    private val state = MutableLiveData<FilterSettingsState>()

    fun getState(): LiveData<FilterSettingsState> = state

    init {
        state.value = FilterSettingsState("", "", 0, false)
    }

    fun changeWorkPlace(newWorkPlace: String) {
        state.value = state.value?.copy(workPlace = newWorkPlace)
    }

    fun changeIndustry(newIndustry: String) {
        state.value = state.value?.copy(industry = newIndustry)
    }

    fun changeSalary(newSalary: Int) {
        state.value = state.value?.copy(salary = newSalary)
    }

    fun changeDontShowWithoutSalary(newDontShow: Boolean) {
        state.value = state.value?.copy(dontShowWithoutSalary = newDontShow)
    }
}
