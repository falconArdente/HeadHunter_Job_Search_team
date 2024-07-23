package ru.practicum.android.diploma.filter.domain.api

import ru.practicum.android.diploma.filter.domain.model.AreaFilter
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.filter.domain.model.CountryFilter

interface PlaceToWorkFilterInteractor {
    fun saveCountry(countryId: String?, countryName: String?)

    fun saveArea(areaId: String?, areaName: String?)

    fun clearCountry()

    fun clearArea()

    fun getCurrentCountryChoice(): CountryFilter?

    fun getCurrentAreaChoice(): AreaFilter?

    fun isRegionInCurrentCountry(): Boolean

    suspend fun getActualCountryForRegion(areaId: String): Pair<String, String>
}
