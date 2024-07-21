package ru.practicum.android.diploma.filter.domain.impl

import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.AreaFilter

interface AreaFilterRepository {
    fun getArea(): AreaFilter

    fun saveArea(area: Area)

    fun clearArea()
}
