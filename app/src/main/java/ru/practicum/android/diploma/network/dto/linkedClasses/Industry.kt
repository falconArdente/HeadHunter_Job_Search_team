package ru.practicum.android.diploma.network.dto.linkedClasses

import com.google.gson.annotations.SerializedName

class Industry(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("industries") val spheres: List<IndustrySphere>
)
