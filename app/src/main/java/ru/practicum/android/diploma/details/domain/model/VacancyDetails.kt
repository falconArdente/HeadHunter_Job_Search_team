package ru.practicum.android.diploma.details.domain.model


class VacancyDetails(
    val id: String,
    val name: String,
    val description: String,
    val employer: EmployerInfo,
    val jobDetails: JobInfo,
    val vacancyUrl: String,
)
