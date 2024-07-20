package ru.practicum.android.diploma.filter.presentation.state

data class FilterSettingsState (
    val workPlace: String,
    val industry: String,
    val salary: String,
    val dontShowWithoutSalary: Boolean
)

