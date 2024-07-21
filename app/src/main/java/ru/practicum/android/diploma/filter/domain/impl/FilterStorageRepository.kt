package ru.practicum.android.diploma.filter.domain.impl

import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.AreaFilter
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.filter.domain.model.CountryFilter
import ru.practicum.android.diploma.filter.domain.model.FilterGeneral
import ru.practicum.android.diploma.filter.domain.model.IndustryDetailsFilterItem
import ru.practicum.android.diploma.filter.domain.model.IndustryFilter

interface FilterStorageRepository {
    fun saveAllFilterParameters(filter: FilterGeneral)

    fun getAllFilterParameters(): FilterGeneral

    fun clearAllFilterParameters()

    fun isFilterActive(): Boolean

    fun getArea(): AreaFilter

    fun saveArea(area: Area)

    fun clearArea()

    fun getCountry(): CountryFilter

    fun saveCountry(country: Country)

    fun clearCountry()

    fun getIndustry(): IndustryFilter

    fun saveIndustry(industry: IndustryDetailsFilterItem)

    fun clearIndustry()

    fun getExpectedSalary(): String

    fun saveExpectedSalary(salaryAmount: String)

    fun clearExpectedSalary()

    fun getHideNoSalaryItems(): Boolean

    fun saveHideNoSalaryItems(hideNoSalaryItems: Boolean)

}
