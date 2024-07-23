package ru.practicum.android.diploma.filter.domain.impl

import android.util.Log
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import ru.practicum.android.diploma.filter.domain.api.PlaceToWorkFilterInteractor
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.AreaFilter
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.filter.domain.model.CountryFilter
import ru.practicum.android.diploma.utils.Resource

class PlaceToWorkFilterInteractorImpl(
    private val filterStorageRepository: FilterStorageRepository,
    private val filterDictionariesRepository: FilterDictionariesRepository,
) :
    PlaceToWorkFilterInteractor {
    override fun saveCountry(countryId: String?, countryName: String?) {
        filterStorageRepository.saveCountry(CountryFilter(countryId = countryId, countryName = countryName))
    }

    override fun saveArea(areaId: String?, areaName: String?) {
        filterStorageRepository.saveArea(AreaFilter(areaId = areaId, areaName = areaName))
    }

    override fun clearCountry() {
        filterStorageRepository.saveCountry(CountryFilter(countryId = null, countryName = null))
    }

    override fun clearArea() {
        filterStorageRepository.saveArea(AreaFilter(areaId = null, areaName = null))
    }

    override fun getCurrentCountryChoice(): CountryFilter? = filterStorageRepository.getAllSavedParameters().country

    override fun getCurrentAreaChoice(): AreaFilter? = filterStorageRepository.getAllSavedParameters().area

    override fun isRegionInCurrentCountry(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getActualCountryForRegion(areaId: String): CountryFilter {
        val result = filterDictionariesRepository.getAreas().first()
        if (result !is Resource.Success) throw IllegalStateException()

        val areaById = result.data!!.associateBy { it.id }
        Log.e("MAP", "${areaById}")

        var area = areaById[areaId] ?: error("Area not found with id $areaId")
        Log.e("MAP", "${area}")

        while (area.parentId != null) {
            area = areaById[area.parentId] ?: error("Parent area by id ${area.parentId} not found")
            Log.e("MAP2", "${area}")
        }

        return CountryFilter(countryId = area.id, countryName = area.name)
    }

    override suspend fun getCountryForRegion(areaId: String): CountryFilter? {
        val result = filterDictionariesRepository.getAreas().first()
        if (result !is Resource.Success) throw IllegalStateException()
        val resultList = result.data!!

        val areaById = findAreaById(areaId, resultList) ?: return null
        var currentArea: Area? = areaById
        while(currentArea?.parentId != null) {
            currentArea = findParentArea(currentArea, resultList)
        }
        return CountryFilter(countryId = currentArea?.id, countryName = currentArea?.name)
    }

    private fun findAreaById(areaId: String, generalListOfAreas: List<Area>?): Area? {
        generalListOfAreas?.forEach { area ->
            if(area.id == areaId) {
                return area
            }
            val subAreaResult = findAreaById(areaId, area.subAreas)
            if (subAreaResult != null) {
                return subAreaResult
            }
        }
        return null
    }

    private fun findParentArea(area: Area, areasList: List<Area>): Area? {
        areasList.forEach { topArea ->
            if (topArea.id == area.parentId) {
                return topArea
            }
            val subAreaResult = findParentArea(area, topArea.subAreas ?: emptyList())
            if (subAreaResult != null) {
                return subAreaResult
            }
        }
        return null
    }
}
