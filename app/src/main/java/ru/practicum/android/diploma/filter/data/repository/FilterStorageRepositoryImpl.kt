package ru.practicum.android.diploma.filter.data.repository

import ru.practicum.android.diploma.filter.data.storage.FilterStorage
import ru.practicum.android.diploma.filter.domain.impl.FilterStorageRepository
import ru.practicum.android.diploma.filter.domain.model.AreaDetailsFilterItem
import ru.practicum.android.diploma.filter.domain.model.AreaFilter
import ru.practicum.android.diploma.filter.domain.model.CountryFilter
import ru.practicum.android.diploma.filter.domain.model.FilterGeneral
import ru.practicum.android.diploma.filter.domain.model.IndustryDetailsFilterItem
import ru.practicum.android.diploma.filter.domain.model.IndustryFilter

class FilterStorageRepositoryImpl(private val filterStorage: FilterStorage) : FilterStorageRepository {
    private val finalFilterSaved = filterStorage.getAllFinalFilterParameters()
    private val specificFilterSaved = filterStorage.getAllSavedParameter()

    override fun saveAllFilterParameters(filter: FilterGeneral) {
        filterStorage.saveFinalFilterParameters(filter)
    }

    override fun getAllFilterParameters(): FilterGeneral = filterStorage.getAllFinalFilterParameters()

    override fun getAllSavedParameters(): FilterGeneral = filterStorage.getAllSavedParameter()

    override fun clearAllFilterParameters() {
        filterStorage.clearAllFilterParameters()
    }

    override fun isFilterActive(): Boolean = (finalFilterSaved.country != null || finalFilterSaved.area != null
        || finalFilterSaved.industry != null || finalFilterSaved.expectedSalary != null
        || finalFilterSaved.hideNoSalaryItems)

    override fun saveArea(area: AreaDetailsFilterItem) {
        filterStorage.saveSpecificFilterParameters(
            specificFilterSaved.copy(area = AreaFilter(areaId = area.areaId, areaName = area.areaName))
        )
    }

    override fun saveCountry(country: AreaDetailsFilterItem) {
        filterStorage.saveSpecificFilterParameters(
            specificFilterSaved.copy(
                country = CountryFilter(
                    countryId = country.areaId,
                    countryName = country.areaName
                )
            )
        )
    }

    override fun saveIndustry(industry: IndustryDetailsFilterItem) {
        filterStorage.saveSpecificFilterParameters(
            specificFilterSaved.copy(
                industry = IndustryFilter(industryId = industry.industryId, industryName = industry.industryName)
            )
        )
    }

    override fun saveExpectedSalary(salaryAmount: String) {
        filterStorage.saveSpecificFilterParameters(specificFilterSaved.copy(expectedSalary = salaryAmount))
    }

    override fun saveHideNoSalaryItems(hideNoSalaryItems: Boolean) {
        filterStorage.saveSpecificFilterParameters(specificFilterSaved.copy(hideNoSalaryItems = hideNoSalaryItems))
    }
}
