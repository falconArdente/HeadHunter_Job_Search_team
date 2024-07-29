package ru.practicum.android.diploma.details.presentation.state

import ru.practicum.android.diploma.details.domain.model.VacancyDetails

sealed class VacancyDetailsState {
    data class Content(val data: VacancyDetails) : VacancyDetailsState()
    data class Error(val message: String) : VacancyDetailsState()
    data class InternetConnectionError(val message: String) : VacancyDetailsState()
    data class ServerError(val message: String) : VacancyDetailsState()
    data class Favorite(val isFavorite: Boolean) : VacancyDetailsState()
    data object Loading : VacancyDetailsState()
}
