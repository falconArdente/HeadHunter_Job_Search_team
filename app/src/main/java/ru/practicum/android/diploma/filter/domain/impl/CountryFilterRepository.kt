package ru.practicum.android.diploma.filter.domain.impl

import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.filter.domain.model.CountryFilter

interface CountryFilterRepository {
    fun getCountry(): CountryFilter

    fun saveCountry(country: Country)

    fun clearCountry()
}
