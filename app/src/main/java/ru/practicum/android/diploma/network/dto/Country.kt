package ru.practicum.android.diploma.network.dto

import com.google.gson.annotations.SerializedName

class Country(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
)
