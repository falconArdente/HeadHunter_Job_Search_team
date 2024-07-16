package ru.practicum.android.diploma.search.domain.model

import ru.practicum.android.diploma.network.data.dto.linked.Contacts

data class Vacancy(
    val id: String,
    val name: String,
    val salary: SalaryModel?,
    val employer: EmployerModel?,
    val brandSnippet: BrandSnippetModel?,
    val contacts: Contacts?,
    val area: AreaModel?,
)
