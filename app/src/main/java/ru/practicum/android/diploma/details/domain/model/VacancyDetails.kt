package ru.practicum.android.diploma.details.domain.model

data class VacancyDetails(
    val title: String,
    val salary: String,
    val company: String,
    val imageUrl: String?,
    val city: String,
    val experience: String,
    val occupation: String,
    val responsibilities: String?,
    val requirements: String?,
    val conditions: String?,
    val keySkills: String?
)
