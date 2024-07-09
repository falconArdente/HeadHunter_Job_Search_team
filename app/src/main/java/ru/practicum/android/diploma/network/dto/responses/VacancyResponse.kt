package ru.practicum.android.diploma.network.dto.responses

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.network.dto.Vacancy

class VacancyResponse(
    @SerializedName("items") val vacancyList: List<Vacancy>,
    @SerializedName("page") val currentPage: Number,
    @SerializedName("found") val totalFound: Number,
    @SerializedName("pages") val totalPages: Number,
    @SerializedName("per_page") val countForPage: Number,
) : Response()
