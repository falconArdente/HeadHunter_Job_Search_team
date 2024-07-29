package ru.practicum.android.diploma.filter.presentation.state

import ru.practicum.android.diploma.filter.domain.model.FilterGeneral

sealed class FilterSettingsState {
    data class Data(
        val filter: FilterGeneral,
        val isActiveApply: Boolean,
        val isActiveReset: Boolean
    ) : FilterSettingsState()
}
