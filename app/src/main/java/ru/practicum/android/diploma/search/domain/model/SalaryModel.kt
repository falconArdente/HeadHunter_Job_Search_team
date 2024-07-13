package ru.practicum.android.diploma.search.domain.model

data class SalaryModel(
    val currency: String,
    val from: Int?,
    val gross: Boolean,
    val to: Int?
)
