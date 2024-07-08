package ru.practicum.android.diploma.network.dto

import com.google.gson.annotations.SerializedName

class IndustrySphere(
    @SerializedName("id") val id: String,
    @SerializedName("name") val sphereName: String,
)
