package ru.practicum.android.diploma.details.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.details.domain.api.DetailsDbInteractor
import ru.practicum.android.diploma.details.domain.api.GetVacancyDetailsUseCase
import ru.practicum.android.diploma.details.domain.api.NavigatorInteractor
import ru.practicum.android.diploma.details.domain.model.VacancyDetails
import ru.practicum.android.diploma.details.presentation.state.VacancyDetailsState
import ru.practicum.android.diploma.utils.Resource

class VacancyDetailsViewModel(
    application: Application,
    private val getVacancyDetailsUseCase: GetVacancyDetailsUseCase,
    private val navigatorInteractor: NavigatorInteractor,
    private val detailsDbInteractor: DetailsDbInteractor,
    private val vacancyId: String,
) : AndroidViewModel(application) {
    private val _stateLiveData = MutableLiveData<VacancyDetailsState>()
    fun getState(): LiveData<VacancyDetailsState> = _stateLiveData



    private var vacancyDetails: VacancyDetails? = null
    private var isFavoriteJob: Job? = null

    init {
        viewModelScope.launch {
            getVacancyDetailsUseCase.execute(vacancyId).collect {
                processSearchVacancyResponse(it)
            }
            detailsDbInteractor.isExistsVacancy(vacancyId.toInt()).collect {
                _stateLiveData.postValue(VacancyDetailsState.Favorite(it))
            }
        }
    }

    private suspend fun processSearchVacancyResponse(searchResult: Resource<VacancyDetails>) {
        if (searchResult.data != null) {
            val vacancyDetailsState = VacancyDetailsState.Content(searchResult.data)
            vacancyDetails = vacancyDetailsState.data
            _stateLiveData.postValue(vacancyDetailsState)
        } else {
            when (searchResult) {
                is Resource.InternetConnectionError -> {
                    var isVacancyInFavorites = false
                    detailsDbInteractor.isExistsVacancy(vacancyId.toInt()).collect { isExists ->
                        isVacancyInFavorites = isExists
                    }
                    if (isVacancyInFavorites) {
                        detailsDbInteractor.getVacancyById(vacancyId.toInt()).collect { vacancyDetails ->
                            _stateLiveData.postValue(VacancyDetailsState.Content(vacancyDetails!!))
                        }
                    } else {
                        _stateLiveData.postValue(VacancyDetailsState.Error(searchResult.message!!))
                    }
                }

                is Resource.NotFoundError -> {
                    var isVacancyInFavorites = false
                    detailsDbInteractor.isExistsVacancy(vacancyId.toInt()).collect { isExists ->
                        isVacancyInFavorites = isExists
                    }
                    if (isVacancyInFavorites) {
                        detailsDbInteractor.deleteVacancy(vacancyId.toInt())
                    }
                    _stateLiveData.postValue(VacancyDetailsState.Error(searchResult.message!!))
                }

                else -> _stateLiveData.postValue(VacancyDetailsState.Error(searchResult.message!!))
            }
        }
    }

    fun onFavoriteClicked() {
        if (isFavoriteJob?.isActive == true) return

        isFavoriteJob = viewModelScope.launch {
                var isInFavoriteList = false
                detailsDbInteractor.isExistsVacancy(vacancyId.toInt()).collect {
                    isInFavoriteList = it
                }
                if (isInFavoriteList) {
                    detailsDbInteractor.deleteVacancy(vacancyId.toInt())
                    _stateLiveData.postValue(VacancyDetailsState.Favorite(false))
                } else {
                    if (vacancyDetails != null) {
                        detailsDbInteractor.insertVacancy(vacancyDetails!!)
                        _stateLiveData.postValue(VacancyDetailsState.Favorite(true))
                    }
                }
        }
    }

    fun shareVacancy() {
        vacancyDetails?.let { navigatorInteractor.shareLink(it.vacancyUrl) }
    }

    fun dialNumber(number: String) {
        navigatorInteractor.dialNumber(number)
    }

    fun sendEmail(email: String) {
        navigatorInteractor.sendEmail(email)
    }

}
