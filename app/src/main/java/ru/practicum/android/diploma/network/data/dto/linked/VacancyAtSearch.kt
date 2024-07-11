package ru.practicum.android.diploma.network.data.dto.linked

import com.google.gson.annotations.SerializedName

class VacancyAtSearch(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("salary") val salary: Salary,
    @SerializedName("url") val url: String,
    @SerializedName("brand_snippet") val brandSnippet: BrandSnippet?,
    @SerializedName("contacts") val contacts: Contacts?,
)
