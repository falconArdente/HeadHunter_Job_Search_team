package ru.practicum.android.diploma.filter.data.repository

import ru.practicum.android.diploma.filter.data.storage.FilterStorage
import ru.practicum.android.diploma.filter.domain.impl.AreaFilterRepository
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.AreaFilter

class AreaFilterRepositoryImpl(private val filterStorage: FilterStorage) : AreaFilterRepository {
    override fun getArea(): AreaFilter {
        val savedFilter = filterStorage.getFilterParameters()
        // ВАЖНО для дальнейшей обработки! если регион ранее не сохранялся в фильтре,
        // т.е. area в Filter null, то вернет пустой объект AreaFilter(), где поля id == null и name == null
        return savedFilter.area ?: AreaFilter()
    }

    override fun saveArea(area: Area) {
        val savedFilter = filterStorage.getFilterParameters()
        filterStorage.saveFilterParameters(
            savedFilter.copy(area = AreaFilter(areaId = area.id, areaName = area.name))
        )
    }

    override fun clearArea() {
        val savedFilter = filterStorage.getFilterParameters()
        filterStorage.saveFilterParameters(savedFilter.copy(area = null))
    }
}
