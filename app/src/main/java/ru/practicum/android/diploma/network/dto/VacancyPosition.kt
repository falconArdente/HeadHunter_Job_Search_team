package ru.practicum.android.diploma.network.dto

import com.google.gson.annotations.SerializedName

class VacancyPosition(
    @SerializedName("id") val id: String,
    @SerializedName("text") val text: String,
)
