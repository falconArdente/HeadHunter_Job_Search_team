package ru.practicum.android.diploma.filter.domain.impl

import android.util.Log
import ru.practicum.android.diploma.filter.domain.api.PlaceToWorkFilterInteractor
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.AreaFilter
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

    override suspend fun getActualCountryForRegion(areaId: String): Pair<String, String> {

        var country: Pair<String, String> = Pair(first = "", second = "")
        filterDictionariesRepository.getAreas().collect { result ->
            when (result) {
                is Resource.Success -> {
                    val listOfAreas = result.data!!
                    val parent = getParent(areaId, listOfAreas)
                    country = parent
                }

                is Resource.Error, is Resource.InternetConnectionError, is Resource.NotFoundError -> country =
                    Pair(first = "", second = "")
            }
        }
        return country
    }

    private fun getParent(areaIdChild: String, generalList: List<Area>): Pair<String, String> {
        for (parent in generalList) {
            if (parent.subAreas?.isNotEmpty() == true) {
                Log.e("NO_INTER","${parent.subAreas}")
                if (parent.subAreas?.any { it.id == areaIdChild } == true) {
                    return Pair(first = parent.parentId!!, second = parent.name)
                }
            }
            val parentArea = parent.subAreas?.let {
                getParent(areaIdChild, it)
            }
            if (parentArea != null) {
                return parentArea
            }
        }
        return Pair(first = "", second = "")
    }
//            when (result) {
//                is Resource.Success -> {
//                    val listOfAreas = result.data!!
//                    if (l)
//                    }
//                    val otherRegionsItem = areasWithParentId.find { it.name == OTHER_REGIONS }
//                    if (otherRegionsItem != null) {
//                        val filteredCountriesList = areasWithParentId - otherRegionsItem
//                        val updatedCountriesList = filteredCountriesList + otherRegionsItem
//                        emit(Resource.Success(updatedCountriesList))
//                    } else emit(Resource.Success(areasWithParentId))
//                }
//
//                is Resource.Error -> emit(Resource.Error(result.message!!))
//
//                is Resource.InternetConnectionError -> emit(Resource.InternetConnectionError(result.message!!))
//
//                is Resource.NotFoundError -> emit(Resource.NotFoundError(result.message!!))
//            }

//        }
//    }
}
