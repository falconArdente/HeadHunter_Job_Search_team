package ru.practicum.android.diploma.details.presentation.state

import ru.practicum.android.diploma.details.domain.model.VacancyDetails

sealed interface VacancyDetailsState {
    class Content(val data: VacancyDetails) : VacancyDetailsState
    class Error(val message: String) : VacancyDetailsState
}

