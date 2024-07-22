package ru.practicum.android.diploma.filter.presentation.state

data class FilterSettingsState(
    val workPlace: String,
    val industry: String,
    val salary: Int,
    val dontShowWithoutSalary: Boolean
)

