package ru.practicum.android.diploma.search.domain.model

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.network.data.dto.linked.BrandSnippet
import ru.practicum.android.diploma.network.data.dto.linked.Contacts
import ru.practicum.android.diploma.network.data.dto.linked.Salary

class VacancyModel(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("salary") val salary: Salary,
    @SerializedName("url") val url: String,
    @SerializedName("brand_snippet") val brandSnippet: BrandSnippet?,
    @SerializedName("contacts") val contacts: Contacts?,
)
