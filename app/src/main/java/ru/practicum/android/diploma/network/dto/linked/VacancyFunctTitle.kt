package ru.practicum.android.diploma.network.dto.linked

import com.google.gson.annotations.SerializedName

class VacancyFunctTitle(
    @SerializedName("id") val id: String,
    @SerializedName("text") val text: String,
)
