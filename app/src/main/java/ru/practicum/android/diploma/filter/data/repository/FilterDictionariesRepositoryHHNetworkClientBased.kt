package ru.practicum.android.diploma.filter.data.repository

import android.content.Context
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.filter.data.mapper.FilterMapper
import ru.practicum.android.diploma.filter.domain.impl.FilterDictionariesRepository
import ru.practicum.android.diploma.filter.domain.model.Area
import ru.practicum.android.diploma.filter.domain.model.AreaSuggestion
import ru.practicum.android.diploma.filter.domain.model.Country
import ru.practicum.android.diploma.filter.domain.model.Industry
import ru.practicum.android.diploma.network.data.api.HeadHunterNetworkClient
import ru.practicum.android.diploma.network.data.api.MAX_AREA_SUGGESTION_REQUEST_TEXT_LENGTH
import ru.practicum.android.diploma.network.data.api.MIN_AREA_SUGGESTION_REQUEST_TEXT_LENGTH
import ru.practicum.android.diploma.network.data.dto.HeadHunterRequest
import ru.practicum.android.diploma.network.data.dto.responses.AreaSuggestionsResponse
import ru.practicum.android.diploma.network.data.dto.responses.AreasResponse
import ru.practicum.android.diploma.network.data.dto.responses.CountriesResponse
import ru.practicum.android.diploma.network.data.dto.responses.IndustryResponse
import ru.practicum.android.diploma.network.data.dto.responses.Response
import ru.practicum.android.diploma.utils.Resource

class FilterDictionariesRepositoryHHNetworkClientBased(private val client: HeadHunterNetworkClient, context: Context) :
    FilterDictionariesRepository {
    private val industriesErrorMessage = context.getString(R.string.net_industry_dictionary_income_error_message)
    private val areasErrorMessage = context.getString(R.string.net_areas_dictionary_income_error_message)
    private val countriesErrorMessage = context.getString(R.string.net_countries_dictionary_income_error_message)
    private val areasSuggestionsErrorMessage =
        context.getString(R.string.net_area_suggestions_income_error_message)
    private val areaSuggestionsRequestLengthErrorMessage =
        context.getString(R.string.net_area_suggestions_request_text_length_error_message)

    override suspend fun getIndustries(): Flow<Resource<List<Industry>>> = flow {
        val response = client.doRequest(HeadHunterRequest.Industries)
        if (response.resultCode == Response.SUCCESS) {
            emit(
                Resource.Success((response as IndustryResponse).industriesList.map { industryDTO ->
                    FilterMapper.toIndustry(industryDTO)
                })
            )
        } else {
            emit(Resource.Error(industriesErrorMessage))
        }
    }

    override suspend fun getAreas(): Flow<Resource<List<Area>>> = flow {
        val response = client.doRequest(HeadHunterRequest.Areas)
        if (response.resultCode == Response.SUCCESS) {
            emit(
                Resource.Success((response as AreasResponse).areasList.map { areaDto ->
                    FilterMapper.toArea(areaDto)
                })
            )
        } else {
            emit(Resource.Error(areasErrorMessage))
        }
    }

    override suspend fun getCountries(): Flow<Resource<List<Country>>> = flow {
        val response = client.doRequest(HeadHunterRequest.Counties)
        if (response.resultCode == Response.SUCCESS) {
            emit(
                Resource.Success((response as CountriesResponse).countriesList.map { countryDto ->
                    FilterMapper.toCountry(countryDto)
                })
            )
        } else {
            emit(Resource.Error(countriesErrorMessage))
        }
    }

    override suspend fun getSubAreas(areaId: String): Flow<Resource<List<Area>>> {
        return flow {
            val response = client.doRequest(HeadHunterRequest.SubAreas(areaId))
            if (response.resultCode == Response.SUCCESS) {
                Log.d("AAAAAAAAA",response.resultCode.toString())
                emit(
                    Resource.Success((response as AreasResponse).areasList.map { areaDto ->
                        FilterMapper.toArea(areaDto)
                    })
                )
            } else {
                emit(Resource.Error(areasErrorMessage))
            }
        }
    }

    override suspend fun searchInAreas(searchText: String, areaId: String?): Flow<Resource<List<AreaSuggestion>>> {
        return flow {
            if (searchText.length in MIN_AREA_SUGGESTION_REQUEST_TEXT_LENGTH..MAX_AREA_SUGGESTION_REQUEST_TEXT_LENGTH) {
                val response = client.doRequest(
                    HeadHunterRequest.SearchInAreas(searchText, areaId)
                )
                when (response.resultCode) {
                    Response.SUCCESS -> {
                        emit(Resource.Success((response as AreaSuggestionsResponse).suggestions.map { areaSuggestionDTO ->
                            FilterMapper.toAreaSuggestion(areaSuggestionDTO)
                        }))
                    }

                    Response.NOT_FOUND -> emit(Resource.NotFoundError(areasSuggestionsErrorMessage))
                    Response.NO_INTERNET -> emit(Resource.InternetConnectionError(areasSuggestionsErrorMessage))
                    else -> emit(Resource.Error(areasSuggestionsErrorMessage))
                }
            } else {
                emit(Resource.Error(areaSuggestionsRequestLengthErrorMessage))
            }
        }
    }
}
