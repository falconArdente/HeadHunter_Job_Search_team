package ru.practicum.android.diploma.filter.data.repository

import ru.practicum.android.diploma.filter.data.storage.FilterStorage
import ru.practicum.android.diploma.filter.domain.impl.AreaFilterRepository
import ru.practicum.android.diploma.filter.domain.model.Area

class AreaFilterRepositoryImpl(private val filterStorage: FilterStorage) : AreaFilterRepository {
    override fun getAreaId(): String {
        val savedFilter = filterStorage.getFilterParameters()
        return savedFilter.areaId ?: ""
    }

    override fun saveArea(area: Area) {
        val savedFilter = filterStorage.getFilterParameters()
        filterStorage.saveFilterParameters(savedFilter.copy(areaId = area.id))
    }

    override fun clearAreaId() {
        val savedFilter = filterStorage.getFilterParameters()
        filterStorage.saveFilterParameters(savedFilter.copy(areaId = null))
    }
}
