package ru.practicum.android.diploma.network.dto

import com.google.gson.annotations.SerializedName

class Vacancy(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("salary") val salary: Salary,
    @SerializedName("url") val url: String,
    @SerializedName("brand_snippet") val brandSnippet: BrandSnippet,
)
