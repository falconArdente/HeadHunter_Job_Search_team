package ru.practicum.android.diploma.filter.presentation.state

import ru.practicum.android.diploma.filter.domain.model.Area

sealed interface CountryFilterState {
    class CountryContent(val listOfCountries: List<Area>) : CountryFilterState
    class Error(val message: String) : CountryFilterState
    data object Loading : CountryFilterState
}
