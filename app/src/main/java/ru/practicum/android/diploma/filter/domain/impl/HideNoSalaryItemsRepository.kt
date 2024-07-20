package ru.practicum.android.diploma.filter.domain.impl

interface HideNoSalaryItemsRepository {
    fun getHideNoSalaryItems(): Boolean

    fun saveHideNoSalaryItems(hideNoSalaryItems: Boolean)
}
