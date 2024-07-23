package ru.practicum.android.diploma.filter.domain.model

class AreaSuggestion(
    val id: String,
    val name: String,
    val url: String,
) : AreaDetailsFilterItem(areaId = id, areaName = name)
