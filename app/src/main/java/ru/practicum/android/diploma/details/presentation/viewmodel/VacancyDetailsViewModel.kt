package ru.practicum.android.diploma.details.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.details.domain.impl.GetVacancyDetailsUseCaseImpl
import ru.practicum.android.diploma.details.domain.impl.NavigatorInteractorImpl
import ru.practicum.android.diploma.details.domain.model.VacancyDetails
import ru.practicum.android.diploma.details.presentation.state.VacancyDetailsState
import ru.practicum.android.diploma.utils.Resource

class VacancyDetailsViewModel(
    application: Application,
    private val getVacancyDetailsUseCase: GetVacancyDetailsUseCaseImpl,
    private val navigatorInteractorImpl: NavigatorInteractorImpl,
    private val vacancyId: String,
) : AndroidViewModel(application) {
    private val _stateLiveData = MutableLiveData<VacancyDetailsState>()
    val stateLiveData: LiveData<VacancyDetailsState> = _stateLiveData

    private var vacancyDetails: VacancyDetails? = null

    fun searchVacancyById() {
        viewModelScope.launch {
            getVacancyDetailsUseCase.execute(vacancyId).collect {
                processSearchVacancyResponse(it)
            }
        }
    }

    private fun processSearchVacancyResponse(searchResult: Resource<VacancyDetails>) {
        if (searchResult.data != null) {
            val vacancyDetailsState = VacancyDetailsState.Content(searchResult.data)
            vacancyDetails = vacancyDetailsState.data
            _stateLiveData.value = vacancyDetailsState
        } else {
            _stateLiveData.value = VacancyDetailsState.Error(searchResult.message!!)
        }
    }

    fun shareVacancy() {
        vacancyDetails?.let { navigatorInteractorImpl.shareLink(it.vacancyUrl) }
    }

    fun dialNumber(number: String) {
        navigatorInteractorImpl.dialNumber(number)
    }

    fun sendEmail(email: String) {
        navigatorInteractorImpl.sendEmail(email)
    }

}
