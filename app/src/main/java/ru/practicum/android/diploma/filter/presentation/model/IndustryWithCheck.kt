package ru.practicum.android.diploma.filter.presentation.model

import ru.practicum.android.diploma.filter.domain.model.Industry

data class IndustryWithCheck(
    val industry: Industry,
    val isChecked: Boolean = false
)
