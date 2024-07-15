package ru.practicum.android.diploma.favorites.presentation.state

import ru.practicum.android.diploma.details.domain.model.VacancyDetails

sealed class FavoritesListState() {
    data object Empty : FavoritesListState()
    data object Error : FavoritesListState()
    data class Success(val listOfFavVacancies: List<VacancyDetails>) : FavoritesListState()
}
