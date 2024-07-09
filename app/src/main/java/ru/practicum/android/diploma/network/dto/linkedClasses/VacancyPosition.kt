package ru.practicum.android.diploma.network.dto.linkedClasses

import com.google.gson.annotations.SerializedName

class VacancyPosition(
    @SerializedName("id") val id: String,
    @SerializedName("text") val text: String,
)
