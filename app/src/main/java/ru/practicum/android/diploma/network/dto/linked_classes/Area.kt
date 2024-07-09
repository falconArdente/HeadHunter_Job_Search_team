package ru.practicum.android.diploma.network.dto.linked_classes

import com.google.gson.annotations.SerializedName

class Area(
    @SerializedName("areas") val subAreas: List<Area>,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("name_prepositional") val prepositional: String?,
    @SerializedName("parent_id") val parentId: String?,
)
