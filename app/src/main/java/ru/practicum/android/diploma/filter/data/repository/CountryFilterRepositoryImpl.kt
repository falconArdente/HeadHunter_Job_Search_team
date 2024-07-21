package ru.practicum.android.diploma.filter.data.repository

import ru.practicum.android.diploma.filter.data.storage.FilterStorage
import ru.practicum.android.diploma.filter.domain.impl.CountryFilterRepository
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.filter.domain.model.CountryFilter

class CountryFilterRepositoryImpl(private val filterStorage: FilterStorage) : CountryFilterRepository {
    override fun getCountry(): CountryFilter {
        val savedFilter = filterStorage.getFilterParameters()
        // ВАЖНО для дальнейшей обработки! если регион ранее не сохранялся в фильтре,т.е. area в Filter null,
        // то вернет пустой объект CountryFilter(), где поля id == null и name == null
        return savedFilter.country ?: CountryFilter()
    }

    override fun saveCountry(country: Country) {
        val savedFilter = filterStorage.getFilterParameters()
        filterStorage.saveFilterParameters(
            savedFilter.copy(country = CountryFilter(countryId = country.id, countryName = country.name))
        )
    }

    override fun clearCountry() {
        val savedFilter = filterStorage.getFilterParameters()
        filterStorage.saveFilterParameters(savedFilter.copy(country = null))
    }
}
