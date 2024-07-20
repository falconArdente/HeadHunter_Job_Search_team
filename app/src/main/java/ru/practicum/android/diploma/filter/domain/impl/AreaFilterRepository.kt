package ru.practicum.android.diploma.filter.domain.impl

import ru.practicum.android.diploma.filter.domain.model.Area

interface AreaFilterRepository {
    fun getAreaId(): String

    fun getAreaName(): String

    fun saveArea(area: Area)

    fun clearArea()
}
