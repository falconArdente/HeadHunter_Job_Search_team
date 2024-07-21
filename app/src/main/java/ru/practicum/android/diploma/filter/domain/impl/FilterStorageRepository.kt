package ru.practicum.android.diploma.filter.domain.impl

import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.filter.domain.model.FilterGeneral
import ru.practicum.android.diploma.filter.domain.model.IndustryDetailsFilterItem

interface FilterStorageRepository {
    fun saveAllFilterParameters(filter: FilterGeneral)

    fun getAllFilterParameters(): FilterGeneral

    fun getAllSavedParameters(): FilterGeneral

    fun clearAllFilterParameters()

    fun isFilterActive(): Boolean

    fun saveArea(area: Area)

    fun saveCountry(country: Country)

    fun saveIndustry(industry: IndustryDetailsFilterItem)

    fun saveExpectedSalary(salaryAmount: String)

    fun saveHideNoSalaryItems(hideNoSalaryItems: Boolean)

}
