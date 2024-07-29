package ru.practicum.android.diploma.search.presentation.state

import ru.practicum.android.diploma.search.domain.model.Vacancy

sealed class SearchFragmentState {
    data class SearchVacancy(
        val searchVacancy: List<Vacancy>,
        val totalFoundVacancy: Int,
        val isLastPage: Boolean
    ) : SearchFragmentState()

    data object ServerError : SearchFragmentState()
    data object NoResult : SearchFragmentState()
    data object Loading : SearchFragmentState()
    data object LoadingNewPage : SearchFragmentState()
    data object NoTextInInputEditText : SearchFragmentState()
}
