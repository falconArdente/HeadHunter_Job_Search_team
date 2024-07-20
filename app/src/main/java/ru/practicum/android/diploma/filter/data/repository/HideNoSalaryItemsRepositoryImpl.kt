package ru.practicum.android.diploma.filter.data.repository

import ru.practicum.android.diploma.filter.data.storage.FilterStorage
import ru.practicum.android.diploma.filter.domain.impl.HideNoSalaryItemsRepository

class HideNoSalaryItemsRepositoryImpl(private val filterStorage: FilterStorage) : HideNoSalaryItemsRepository {
    override fun getHideNoSalaryItems(): Boolean {
        val savedFilter = filterStorage.getFilterParameters()
        return savedFilter.hideNoSalaryItems
    }

    override fun saveHideNoSalaryItems(hideNoSalaryItems: Boolean) {
        val savedFilter = filterStorage.getFilterParameters()
        filterStorage.saveFilterParameters(savedFilter.copy(hideNoSalaryItems = hideNoSalaryItems))
    }
}
