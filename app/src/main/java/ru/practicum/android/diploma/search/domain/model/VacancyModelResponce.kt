package ru.practicum.android.diploma.search.domain.model

import com.google.gson.annotations.SerializedName

class VacancyModelResponce(
    @SerializedName("items") val vacancyAtSearchList: List<VacancyModel>,
    @SerializedName("page") val currentPage: Number,
    @SerializedName("found") val totalFound: Number,
    @SerializedName("pages") val totalPages: Number,
    @SerializedName("per_page") val countForPage: Number,
)
