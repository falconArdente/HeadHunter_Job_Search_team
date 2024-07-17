package ru.practicum.android.diploma.details.domain.model

class VacancyDetails(
    val id: String,
    val name: String,
    val description: String,
    val employerInfo: EmployerInfo,
    val jobInfo: JobInfo,
    val vacancyUrl: String,
)
