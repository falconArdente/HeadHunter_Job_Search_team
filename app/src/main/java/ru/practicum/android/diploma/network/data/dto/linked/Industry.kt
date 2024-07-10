package ru.practicum.android.diploma.network.data.dto.linked

import com.google.gson.annotations.SerializedName

class Industry(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("industries") val spheres: List<ru.practicum.android.diploma.network.data.dto.linked.IndustrySphere>
)
