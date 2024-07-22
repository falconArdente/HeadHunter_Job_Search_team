package ru.practicum.android.diploma.filter.domain.impl

import ru.practicum.android.diploma.filter.domain.api.FilterStorageInteractor
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.filter.domain.model.FilterGeneral
import ru.practicum.android.diploma.filter.domain.model.IndustryDetailsFilterItem

class FilterStorageInteractorImpl(
    private val repository: FilterStorageRepository
) : FilterStorageInteractor {
    override fun clearAllFilterParameters() {
        repository.clearAllFilterParameters()
    }

    override fun isFilterActive(): Boolean {
        return repository.isFilterActive()
    }

    override fun saveArea(area: Area) {
        repository.saveArea(area)
    }

    override fun saveCountry(country: Country) {
        repository.saveCountry(country)
    }

    override fun saveIndustry(industry: IndustryDetailsFilterItem) {
        repository.saveIndustry(industry)
    }

    override fun saveExpectedSalary(salaryAmount: String) {
        repository.saveExpectedSalary(salaryAmount)
    }

    override fun saveHideNoSalaryItems(hideNoSalaryItems: Boolean) {
        repository.saveHideNoSalaryItems(hideNoSalaryItems)
    }

    override fun getAllFilterParameters(): FilterGeneral {
        return repository.getAllFilterParameters()
    }

    override fun getAllSavedParameters(): FilterGeneral {
        return repository.getAllSavedParameters()
    }

    override fun saveAllFilterParameters(filter: FilterGeneral) {
        repository.saveAllFilterParameters(filter)
    }
}
