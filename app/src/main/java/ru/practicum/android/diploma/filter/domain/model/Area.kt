package ru.practicum.android.diploma.filter.domain.model

data class Area(
    val subAreas: List<Area>?,
    val id: String,
    val name: String,
    val parentId: String?
)
