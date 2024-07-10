package ru.practicum.android.diploma.network.dto.responses

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.network.dto.linked.VacancyAtSearch

class VacancyListResponse(
    @SerializedName("items") val vacancyAtSearchList: List<VacancyAtSearch>,
    @SerializedName("page") val currentPage: Number,
    @SerializedName("found") val totalFound: Number,
    @SerializedName("pages") val totalPages: Number,
    @SerializedName("per_page") val countForPage: Number,
) : Response()
