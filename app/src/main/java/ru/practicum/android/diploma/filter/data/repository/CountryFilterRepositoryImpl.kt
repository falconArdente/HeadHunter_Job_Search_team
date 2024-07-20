package ru.practicum.android.diploma.filter.data.repository

import ru.practicum.android.diploma.filter.data.storage.FilterStorage
import ru.practicum.android.diploma.filter.domain.impl.CountryFilterRepository
import ru.practicum.android.diploma.filter.domain.model.Country

class CountryFilterRepositoryImpl(private val filterStorage: FilterStorage) : CountryFilterRepository {
    override fun getCountryId(): String {
        val savedFilter = filterStorage.getFilterParameters()
        return savedFilter.countryId ?: ""
    }

    override fun saveCountry(country: Country) {
        val savedFilter = filterStorage.getFilterParameters()
        filterStorage.saveFilterParameters(savedFilter.copy(countryId = country.id))
    }

    override fun clearCountryId() {
        val savedFilter = filterStorage.getFilterParameters()
        filterStorage.saveFilterParameters(savedFilter.copy(countryId = null))
    }
}
