package ru.practicum.android.diploma.search.domain.model

import ru.practicum.android.diploma.network.data.dto.linked.Contacts

data class Vacancy(
    val id: String,
    var name: String,
    val salary: SalaryModel?,
    var employer: EmployerModel?,
    val brandSnippet: BrandSnippetModel?,
    val contacts: Contacts?,
    var area: AreaModel?,
)
