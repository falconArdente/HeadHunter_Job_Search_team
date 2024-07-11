package ru.practicum.android.diploma.search.domain.model

import ru.practicum.android.diploma.network.data.dto.linked.Employer
import ru.practicum.android.diploma.network.data.dto.linked.Salary

class Vacancy(
    val id: String,
    val name: String,
    val salary: SalaryDomain,
    val url: String,
    val brandSnippet: BrandSnippetDomain?,
    val contacts: ContactsDomain?,
    val area: CityAreaDomain,
    val employer: Employer,
)
