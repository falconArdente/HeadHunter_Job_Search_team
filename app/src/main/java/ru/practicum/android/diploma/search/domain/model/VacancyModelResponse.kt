package ru.practicum.android.diploma.search.domain.model

data class VacancyModelResponse(
    val vacancyAtSearchList: List<Vacancy>,
    val currentPage: Number,
    val totalFound: Number,
    val totalPages: Number,
    val countForPage: Number,
)
