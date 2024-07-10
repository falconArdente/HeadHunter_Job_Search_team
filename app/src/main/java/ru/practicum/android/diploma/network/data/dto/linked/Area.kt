package ru.practicum.android.diploma.network.data.dto.linked

import com.google.gson.annotations.SerializedName

class Area(
    @SerializedName("areas") val subAreas: List<ru.practicum.android.diploma.network.data.dto.linked.Area>,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("name_prepositional") val prepositional: String?,
    @SerializedName("parent_id") val parentId: String?,
)
