package ru.practicum.android.diploma.search.domain.model

import ru.practicum.android.diploma.network.data.dto.linked.BrandSnippet
import ru.practicum.android.diploma.network.data.dto.linked.Contacts
import ru.practicum.android.diploma.network.data.dto.linked.Salary

data class Vacancy(
    val id: String,
    val name: String,
    val salary: Salary,
    val url: String,
    val brandSnippet: BrandSnippet?,
    val contacts: Contacts?,
)
