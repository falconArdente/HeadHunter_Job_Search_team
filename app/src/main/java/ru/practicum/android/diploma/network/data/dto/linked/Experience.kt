package ru.practicum.android.diploma.network.data.dto.linked

import com.google.gson.annotations.SerializedName

class Experience(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
)
