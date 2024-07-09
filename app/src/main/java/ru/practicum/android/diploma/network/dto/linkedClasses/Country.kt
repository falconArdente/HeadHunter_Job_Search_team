package ru.practicum.android.diploma.network.dto.linkedClasses

import com.google.gson.annotations.SerializedName

class Country(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
)
