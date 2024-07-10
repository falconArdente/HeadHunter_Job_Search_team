package ru.practicum.android.diploma.network.data.dto.linked

import com.google.gson.annotations.SerializedName

class VacancyAtSearch(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("salary") val salary: ru.practicum.android.diploma.network.data.dto.linked.Salary,
    @SerializedName("url") val url: String,
    @SerializedName("brand_snippet") val brandSnippet: ru.practicum.android.diploma.network.data.dto.linked.BrandSnippet?,
    @SerializedName("contacts") val contacts: ru.practicum.android.diploma.network.data.dto.linked.Contacts?,
)
