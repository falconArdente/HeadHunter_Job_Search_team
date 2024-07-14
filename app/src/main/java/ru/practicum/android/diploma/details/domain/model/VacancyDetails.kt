package ru.practicum.android.diploma.details.domain.model


class VacancyDetails(
    val id: String,
    val name: String,
    val description: String,
    val employer: EmployerDetails?,
    val salary: SalaryDetails?,
    val contacts: ContactsDetails?,
    val area: AreaDetails,
    val experience: ExperienceDetails?,
    val employment: EmploymentDetails?,
    val keySkills: List<String>,
    val vacancyUrl: String,
)
