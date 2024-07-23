package ru.practicum.android.diploma.filter.presentation.state

import ru.practicum.android.diploma.filter.domain.model.AreaDetailsFilterItem

sealed class AreaFilterState {
    class AreaContent(val listOfAreas: List<AreaDetailsFilterItem>) : AreaFilterState()
    class Error(val message: String) : AreaFilterState()
    data object Loading : AreaFilterState()
    data object Empty : AreaFilterState()
}
