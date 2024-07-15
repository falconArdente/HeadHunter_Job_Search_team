package ru.practicum.android.diploma.search.presentation.state

import ru.practicum.android.diploma.search.domain.model.Vacancy

sealed interface SearchFragmentState {
    data class SearchVacancy(val searchVacancy: List<Vacancy>) : SearchFragmentState
    object ServerError : SearchFragmentState
    object NoResult : SearchFragmentState
    object Loading : SearchFragmentState
    object NoTextInInputEditText : SearchFragmentState
}
