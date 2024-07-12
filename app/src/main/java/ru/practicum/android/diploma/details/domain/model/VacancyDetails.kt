package ru.practicum.android.diploma.details.domain.model

import ru.practicum.android.diploma.network.data.dto.linked.Contacts
import ru.practicum.android.diploma.network.data.dto.linked.Employer
import ru.practicum.android.diploma.network.data.dto.linked.Employment
import ru.practicum.android.diploma.network.data.dto.linked.Salary

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
    val keySkills: List<SkillDetails>,
    val vacancyUrl: String,
)
