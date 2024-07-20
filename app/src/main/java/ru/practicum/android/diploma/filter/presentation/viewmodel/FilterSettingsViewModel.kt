package ru.practicum.android.diploma.filter.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent
import ru.practicum.android.diploma.filter.presentation.state.FilterSettingsState

class FilterSettingsViewModel : ViewModel(), KoinComponent {

    private val state = MutableLiveData<FilterSettingsState>()

    fun getState(): LiveData<FilterSettingsState> = state

    init {
        state.value = FilterSettingsState("", "", "", false)
    }

    fun changeWorkPlace(newWorkPlace: String) {
        state.value = state.value?.copy(workPlace = newWorkPlace)
    }

    fun changeIndustry(newIndustry: String) {
        state.value = state.value?.copy(industry = newIndustry)
    }

    fun changeSalary(newSalary: String) {
        state.value = state.value?.copy(salary = newSalary)
    }

    fun changeDontShowWithoutSalary(newDontShow: Boolean) {
        state.value = state.value?.copy(dontShowWithoutSalary = newDontShow)
    }
}
