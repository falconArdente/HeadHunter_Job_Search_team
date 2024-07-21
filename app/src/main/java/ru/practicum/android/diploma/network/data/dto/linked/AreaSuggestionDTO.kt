package ru.practicum.android.diploma.network.data.dto.linked

import com.google.gson.annotations.SerializedName

class AreaSuggestionDTO(
    @SerializedName("id") val id: String,
    @SerializedName("text") val name: String,
    @SerializedName("url") val url: String,
)
