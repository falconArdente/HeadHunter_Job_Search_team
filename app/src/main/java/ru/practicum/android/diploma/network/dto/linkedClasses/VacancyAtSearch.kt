package ru.practicum.android.diploma.network.dto.linkedClasses

import com.google.gson.annotations.SerializedName

class VacancyAtSearch(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("salary") val salary: Salary,
    @SerializedName("url") val url: String,
    @SerializedName("brand_snippet") val brandSnippet: BrandSnippet?,
)
