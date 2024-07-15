package ru.practicum.android.diploma.search.domain.model

import ru.practicum.android.diploma.network.data.dto.linked.Contacts

data class Vacancy(
    val id: String,
    val name: String,
    val salary: SalaryModel,
    val url: String,
    val brandSnippet: BrandSnippetModel?,
    val contacts: Contacts?,
    var area: AreaModel,
    var employer: EmployerModel
)
