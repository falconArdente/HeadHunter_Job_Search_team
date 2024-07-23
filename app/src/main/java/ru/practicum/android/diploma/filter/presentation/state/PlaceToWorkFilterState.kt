package ru.practicum.android.diploma.filter.presentation.state

sealed class PlaceToWorkFilterState {
    class AreaFilter(
        val countryId: String? = null,
        val countryName: String? = null,
        val areaId: String? = null,
        val areaName: String? = null,
    ) : PlaceToWorkFilterState()
}
