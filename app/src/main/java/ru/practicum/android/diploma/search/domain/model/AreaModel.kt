package ru.practicum.android.diploma.search.domain.model

import com.google.gson.annotations.SerializedName

 data class AreaModel(
    val subAreas: List<AreaModel>,
    val id: String,
    val name: String,
    val prepositional: String?,
    val parentId: String?,
) {
}
