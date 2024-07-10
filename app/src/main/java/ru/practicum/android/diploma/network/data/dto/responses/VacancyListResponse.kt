package ru.practicum.android.diploma.network.data.dto.responses

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.network.data.dto.linked.VacancyAtSearch

class VacancyListResponse(
    @SerializedName("items") val vacancyAtSearchList: List<ru.practicum.android.diploma.network.data.dto.linked.VacancyAtSearch>,
    @SerializedName("page") val currentPage: Number,
    @SerializedName("found") val totalFound: Number,
    @SerializedName("pages") val totalPages: Number,
    @SerializedName("per_page") val countForPage: Number,
) : ru.practicum.android.diploma.network.data.dto.responses.Response()
