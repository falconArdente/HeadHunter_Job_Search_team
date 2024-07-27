package ru.practicum.android.diploma.filter.data.repository

import ru.practicum.android.diploma.filter.data.storage.FilterStorage
import ru.practicum.android.diploma.filter.domain.impl.FilterStorageRepository
import ru.practicum.android.diploma.filter.domain.model.AreaFilter
import ru.practicum.android.diploma.filter.domain.model.CountryFilter
import ru.practicum.android.diploma.filter.domain.model.FilterGeneral
import ru.practicum.android.diploma.filter.domain.model.IndustryDetailsFilterItem
import ru.practicum.android.diploma.filter.domain.model.IndustryFilter

class FilterStorageRepositoryImpl(private val filterStorage: FilterStorage) :
    FilterStorageRepository {
    private val finalFilterSaved = filterStorage.getAllFinalFilterParameters()
    private var specificFilterSaved = filterStorage.getAllSavedParameter()
    override fun saveAllFilterParameters(filter: FilterGeneral) {
        filterStorage.saveFinalFilterParameters(filter)
    }

    override fun getAllFilterParameters(): FilterGeneral = filterStorage.getAllFinalFilterParameters()

    override fun getAllSavedParameters(): FilterGeneral = filterStorage.getAllSavedParameter()

    override fun clearAllFilterParameters() {
        filterStorage.clearAllFilterParameters()
    }

    override fun isFilterActive(): Boolean {
        val finalFilterSaved = filterStorage.getAllFinalFilterParameters()
        return (finalFilterSaved.country != null || finalFilterSaved.area != null
            || finalFilterSaved.industry != null || finalFilterSaved.expectedSalary != null
            || finalFilterSaved.hideNoSalaryItems)
    }

    override fun saveArea(area: AreaFilter) {
        specificFilterSaved = specificFilterSaved.copy(area = area)
        filterStorage.saveSpecificFilterParameters(specificFilterSaved)
    }

    override fun saveCountry(country: CountryFilter) {
        specificFilterSaved = specificFilterSaved.copy(country = country)
        filterStorage.saveSpecificFilterParameters(specificFilterSaved)
    }

    override fun saveIndustry(industry: IndustryDetailsFilterItem) {
        specificFilterSaved = specificFilterSaved.copy(
            industry = IndustryFilter(industryId = industry.industryId, industryName = industry.industryName)
        )
        filterStorage.saveSpecificFilterParameters(specificFilterSaved)
    }

    override fun saveExpectedSalary(salaryAmount: String) {
        specificFilterSaved = specificFilterSaved.copy(expectedSalary = salaryAmount)
        filterStorage.saveSpecificFilterParameters(specificFilterSaved)
    }

    override fun saveHideNoSalaryItems(hideNoSalaryItems: Boolean) {
        specificFilterSaved = specificFilterSaved.copy(hideNoSalaryItems = hideNoSalaryItems)
        filterStorage.saveSpecificFilterParameters(specificFilterSaved)

    }
}
