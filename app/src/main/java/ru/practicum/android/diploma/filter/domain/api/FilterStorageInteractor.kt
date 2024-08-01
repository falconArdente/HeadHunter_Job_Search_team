package ru.practicum.android.diploma.filter.domain.api

import ru.practicum.android.diploma.filter.domain.model.AreaFilter
import ru.practicum.android.diploma.filter.domain.model.CountryFilter
import ru.practicum.android.diploma.filter.domain.model.FilterGeneral
import ru.practicum.android.diploma.filter.domain.model.IndustryFilter

interface FilterStorageInteractor {
    fun saveAllFilterParameters(filter: FilterGeneral)

    fun getAllFilterParameters(): FilterGeneral

    fun getAllSavedParameters(): FilterGeneral

    fun clearAllFilterParameters()

    fun isFilterActive(): Boolean

    fun saveArea(area: AreaFilter?)

    fun saveCountry(country: CountryFilter?)

    fun saveIndustry(industry: IndustryFilter?)

    fun saveExpectedSalary(salaryAmount: String)

    fun saveHideNoSalaryItems(hideNoSalaryItems: Boolean)
    fun clearAllSavedParameters()
}
