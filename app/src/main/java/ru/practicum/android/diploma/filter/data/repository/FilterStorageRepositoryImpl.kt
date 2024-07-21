package ru.practicum.android.diploma.filter.data.repository

import ru.practicum.android.diploma.filter.data.storage.FilterStorage
import ru.practicum.android.diploma.filter.domain.impl.FilterStorageRepository
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.AreaFilter
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.filter.domain.model.CountryFilter
import ru.practicum.android.diploma.filter.domain.model.FilterGeneral
import ru.practicum.android.diploma.filter.domain.model.IndustryDetailsFilterItem
import ru.practicum.android.diploma.filter.domain.model.IndustryFilter

class FilterStorageRepositoryImpl(private val filterStorage: FilterStorage) : FilterStorageRepository {
    private val savedFilter = filterStorage.getFilterParameters()

    override fun saveAllFilterParameters(filter: FilterGeneral) {
        filterStorage.saveFilterParameters(filter)
    }

    override fun getAllFilterParameters(): FilterGeneral = filterStorage.getFilterParameters()

    override fun clearAllFilterParameters() {
        filterStorage.clearAllFilterParameters()
    }

    override fun isFilterActive(): Boolean = (savedFilter.country != null || savedFilter.area != null
        || savedFilter.industry != null || savedFilter.expectedSalary != null || savedFilter.hideNoSalaryItems)

    // ВАЖНО для дальнейшей обработки! если регион ранее не сохранялся в фильтре,
    // т.е. area в Filter null, то вернет пустой объект AreaFilter(), где поля id == null и name == null
    override fun getArea(): AreaFilter = savedFilter.area ?: AreaFilter()

    override fun saveArea(area: Area) {
        filterStorage.saveFilterParameters(
            savedFilter.copy(area = AreaFilter(areaId = area.id, areaName = area.name))
        )
    }

    override fun clearArea() {
        filterStorage.saveFilterParameters(savedFilter.copy(area = null))
    }

    // ВАЖНО для дальнейшей обработки! если регион ранее не сохранялся в фильтре,т.е. area в Filter null,
    // то вернет пустой объект CountryFilter(), где поля id == null и name == null
    override fun getCountry(): CountryFilter = savedFilter.country ?: CountryFilter()

    override fun saveCountry(country: Country) {
        filterStorage.saveFilterParameters(
            savedFilter.copy(country = CountryFilter(countryId = country.id, countryName = country.name))
        )
    }

    override fun clearCountry() {
        filterStorage.saveFilterParameters(savedFilter.copy(country = null))
    }

    // ВАЖНО для дальнейшей обработки! если регион ранее не сохранялся в фильтре,т.е. area в Filter null,
    // то вернет пустой объект IndustryFilter(), где поля id == null и name == null
    override fun getIndustry(): IndustryFilter = savedFilter.industry ?: IndustryFilter()

    override fun saveIndustry(industry: IndustryDetailsFilterItem) {
        filterStorage.saveFilterParameters(
            savedFilter.copy(
                industry = IndustryFilter(industryId = industry.industryId, industryName = industry.industryName)
            )
        )
    }

    override fun clearIndustry() {
        filterStorage.saveFilterParameters(savedFilter.copy(industry = null))
    }

    override fun getExpectedSalary(): String = savedFilter.expectedSalary ?: ""

    override fun saveExpectedSalary(salaryAmount: String) {
        filterStorage.saveFilterParameters(savedFilter.copy(expectedSalary = salaryAmount))
    }

    override fun clearExpectedSalary() {
        filterStorage.saveFilterParameters(savedFilter.copy(expectedSalary = null))
    }

    override fun getHideNoSalaryItems(): Boolean = savedFilter.hideNoSalaryItems

    override fun saveHideNoSalaryItems(hideNoSalaryItems: Boolean) {
        filterStorage.saveFilterParameters(savedFilter.copy(hideNoSalaryItems = hideNoSalaryItems))
    }
}
