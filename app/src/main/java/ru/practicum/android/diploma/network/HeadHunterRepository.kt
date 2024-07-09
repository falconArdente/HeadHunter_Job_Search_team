package ru.practicum.android.diploma.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.network.api.HeadHunterNetworkClient
import ru.practicum.android.diploma.network.dto.Area
import ru.practicum.android.diploma.network.dto.Country
import ru.practicum.android.diploma.network.dto.HeadHunterRequest
import ru.practicum.android.diploma.network.dto.Industry
import ru.practicum.android.diploma.network.dto.Locale
import ru.practicum.android.diploma.network.dto.VacancyPosition
import ru.practicum.android.diploma.network.dto.responses.AreasResponse
import ru.practicum.android.diploma.network.dto.responses.CountriesResponse
import ru.practicum.android.diploma.network.dto.responses.DictionariesResponse
import ru.practicum.android.diploma.network.dto.responses.IndustryResponse
import ru.practicum.android.diploma.network.dto.responses.LocalesResponse
import ru.practicum.android.diploma.network.dto.responses.Response
import ru.practicum.android.diploma.network.dto.responses.VacancyResponse
import ru.practicum.android.diploma.network.dto.responses.VacancySuggestionsResponse
import ru.practicum.android.diploma.search.data.repository.SearchRepository
import ru.practicum.android.diploma.utils.Resource

class HeadHunterRepository(private val client: HeadHunterNetworkClient) : SearchRepository {

    override suspend fun getLocales(): Flow<Resource<List<Locale>>> = flow {
        val response = client.doRequest(HeadHunterRequest.Locales)
        if (response.resultCode == Response.SUCCESS) {
            emit(Resource.Success((response as LocalesResponse).localeList))
        } else {
            emit(Resource.Error(""))
        }
    }

    override suspend fun getDictionaries(): Flow<Resource<DictionariesResponse>> = flow {
        val response = client.doRequest(HeadHunterRequest.Dictionaries)
        if (response.resultCode == Response.SUCCESS) {
            emit(Resource.Success(response as DictionariesResponse))
        } else {
            emit(Resource.Error("dictionary error"))
        }
    }

    override suspend fun getIndustries(): Flow<Resource<List<Industry>>> = flow {
        val response = client.doRequest(HeadHunterRequest.Industries)
        if (response.resultCode == Response.SUCCESS) {
            emit(Resource.Success((response as IndustryResponse).industriesList))
        } else {
            emit(Resource.Error("industries error"))
        }
    }

    override suspend fun getAreas(): Flow<Resource<List<Area>>> = flow {
        val response = client.doRequest(HeadHunterRequest.Areas)
        if (response.resultCode == Response.SUCCESS) {
            emit(Resource.Success((response as AreasResponse).areasList))
        } else {
            emit(Resource.Error("areas error"))
        }
    }

    override suspend fun getCountries(): Flow<Resource<List<Country>>> = flow {
        val response = client.doRequest(HeadHunterRequest.Counties)
        if (response.resultCode == Response.SUCCESS) {
            emit(Resource.Success((response as CountriesResponse).countriesList))
        } else {
            emit(Resource.Error("countries error"))
        }
    }

    override suspend fun getVacancySuggestions(textForSuggestions: String): Flow<Resource<List<VacancyPosition>>> = flow {
        if (textForSuggestions.length in 2..3000) {
            val response = client.doRequest(HeadHunterRequest.VacancySuggestions(textForSuggestions))
            if (response.resultCode == Response.SUCCESS) {
                emit(Resource.Success((response as VacancySuggestionsResponse).vacancyPositionsList))
            } else {
                emit(Resource.Error("Vacancy suggestions error"))
            }
        } else emit(Resource.Error("Vacancy suggestion text length should be 2..3000"))
    }
    override suspend fun getVacancy(textForSearch: String): Flow<Resource<VacancyResponse>> = flow {
            val response = client.doRequest(HeadHunterRequest.Vacancy(textForSearch))
            if (response.resultCode == Response.SUCCESS) {
                emit(Resource.Success(response as VacancyResponse))
            } else {
                emit(Resource.Error("Vacancy search error"))
            }
    }
}
