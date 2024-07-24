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
    private val _stateLiveData = MutableLiveData<VacancyDetailsState>(VacancyDetailsState.Loading)
    val stateLiveData: LiveData<VacancyDetailsState> = _stateLiveData

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    private var vacancyDetails: VacancyDetails? = null
    private var isFavoriteJob: Job? = null

    init {
        viewModelScope.launch {
            getVacancyDetailsUseCase.execute(vacancyId).collect {
                processSearchVacancyResponse(it)
            }
            detailsDbInteractor.isExistsVacancy(vacancyId.toInt()).collect {
                _isFavorite.value = it
            }
        }
    }

    private suspend fun processSearchVacancyResponse(searchResult: Resource<VacancyDetails>) {
        if (searchResult.data != null) {
            val vacancyDetailsState = VacancyDetailsState.Content(searchResult.data)
            vacancyDetails = vacancyDetailsState.data
            _stateLiveData.value = vacancyDetailsState
        } else {
            when (searchResult) {
                is Resource.InternetConnectionError -> {
                    var isVacancyInFavorites = false
                    detailsDbInteractor.isExistsVacancy(vacancyId.toInt()).collect { isExists ->
                        isVacancyInFavorites = isExists
                    }
                    if (isVacancyInFavorites) {
                        detailsDbInteractor.getVacancyById(vacancyId.toInt()).collect { vacancyDetails ->
                            _stateLiveData.value = VacancyDetailsState.Content(vacancyDetails!!)
                        }
                    } else {
                        _stateLiveData.value = VacancyDetailsState.Error(searchResult.message!!)
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
                    _stateLiveData.value = VacancyDetailsState.Error(searchResult.message!!)
                }

                else -> _stateLiveData.value = VacancyDetailsState.Error(searchResult.message!!)
            }
        }
    }

    fun onFavoriteClicked() {
        if (isFavoriteJob?.isActive == true) return

        isFavoriteJob = viewModelScope.launch {
            if (_stateLiveData.value is VacancyDetailsState.Content) {
                var isInFavoriteList = false
                detailsDbInteractor.isExistsVacancy(vacancyId.toInt()).collect {
                    isInFavoriteList = it
                }
                if (isInFavoriteList) {
                    detailsDbInteractor.deleteVacancy(vacancyId.toInt())
                    _isFavorite.value = false
                } else {
                    detailsDbInteractor.insertVacancy((_stateLiveData.value as VacancyDetailsState.Content).data)
                    _isFavorite.value = true
                }
            } else {
                Unit
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
