package ru.practicum.android.diploma.network.data

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.details.domain.impl.VacancyDetailsRepository
import ru.practicum.android.diploma.details.domain.model.VacancyDetails
import ru.practicum.android.diploma.network.data.api.HeadHunterNetworkClient
import ru.practicum.android.diploma.network.data.api.MAX_VACANCY_SUGGESTION_REQUEST_TEXT_LENGTH
import ru.practicum.android.diploma.network.data.api.MIN_VACANCY_SUGGESTION_REQUEST_TEXT_LENGTH
import ru.practicum.android.diploma.network.data.dto.HeadHunterRequest
import ru.practicum.android.diploma.network.data.dto.linked.Area
import ru.practicum.android.diploma.network.data.dto.linked.Country
import ru.practicum.android.diploma.network.data.dto.linked.Industry
import ru.practicum.android.diploma.network.data.dto.linked.Locale
import ru.practicum.android.diploma.network.data.dto.linked.VacancyFunctTitle
import ru.practicum.android.diploma.network.data.dto.responses.AreasResponse
import ru.practicum.android.diploma.network.data.dto.responses.CountriesResponse
import ru.practicum.android.diploma.network.data.dto.responses.DictionariesResponse
import ru.practicum.android.diploma.network.data.dto.responses.IndustryResponse
import ru.practicum.android.diploma.network.data.dto.responses.LocalesResponse
import ru.practicum.android.diploma.network.data.dto.responses.Response
import ru.practicum.android.diploma.network.data.dto.responses.VacancyByIdResponse
import ru.practicum.android.diploma.network.data.dto.responses.VacancyListResponse
import ru.practicum.android.diploma.network.data.dto.responses.VacancySuggestionsResponse
import ru.practicum.android.diploma.network.data.mapper.VacancyDetailsMapper.mapToDomain
import ru.practicum.android.diploma.search.data.repository.SearchRepository
import ru.practicum.android.diploma.utils.Resource

class HeadHunterRepository(private val client: HeadHunterNetworkClient, context: Context) : SearchRepository,
    VacancyDetailsRepository {
    private val commonDictionaryErrorMessage = context.getString(R.string.net_common_dictionary_income_error_message)
    private val localeDictionaryErrorMessage = context.getString(R.string.net_locales_dictionary_income_error_message)
    private val industriesErrorMessage = context.getString(R.string.net_industry_dictionary_income_error_message)
    private val areasErrorMessage = context.getString(R.string.net_areas_dictionary_income_error_message)
    private val countriesErrorMessage = context.getString(R.string.net_countries_dictionary_income_error_message)
    private val vacancySuggestionsErrorMessage =
        context.getString(R.string.net_vacancy_suggestions_income_error_message)
    private val vacancySuggestionsRequestLengthErrorMessage =
        context.getString(R.string.net_vacancy_suggestions_request_text_length_error_message)
    private val vacancySearchErrorMessage = context.getString(R.string.net_vacancy_search_income_error_message)
    private val vacancyGetByIdErrorMessage = context.getString(R.string.net_vacancy_get_by_id_income_error_message)

    override suspend fun getLocales(): Flow<Resource<List<Locale>>> = flow {
        val response = client.doRequest(HeadHunterRequest.Locales)
        if (response.resultCode == Response.SUCCESS) {
            emit(Resource.Success((response as LocalesResponse).localeList))
        } else {
            emit(Resource.Error(localeDictionaryErrorMessage))
        }
    }

    override suspend fun getDictionaries(): Flow<Resource<DictionariesResponse>> =
        flow {
            val response =
                client.doRequest(HeadHunterRequest.Dictionaries)
            if (response.resultCode == Response.SUCCESS) {
                emit(Resource.Success(response as DictionariesResponse))
            } else {
                emit(Resource.Error(commonDictionaryErrorMessage))
            }
        }

    override suspend fun getIndustries(): Flow<Resource<List<Industry>>> =
        flow {
            val response = client.doRequest(HeadHunterRequest.Industries)
            if (response.resultCode == Response.SUCCESS) {
                emit(Resource.Success((response as IndustryResponse).industriesList))
            } else {
                emit(Resource.Error(industriesErrorMessage))
            }
        }

    override suspend fun getAreas(): Flow<Resource<List<Area>>> =
        flow {
            val response = client.doRequest(HeadHunterRequest.Areas)
            if (response.resultCode == Response.SUCCESS) {
                emit(Resource.Success((response as AreasResponse).areasList))
            } else {
                emit(Resource.Error(areasErrorMessage))
            }
        }

    override suspend fun getCountries(): Flow<Resource<List<Country>>> =
        flow {
            val response = client.doRequest(HeadHunterRequest.Counties)
            if (response.resultCode == Response.SUCCESS) {
                emit(Resource.Success((response as CountriesResponse).countriesList))
            } else {
                emit(Resource.Error(countriesErrorMessage))
            }
        }

    override suspend fun getVacancySuggestions(textForSuggestions: String): Flow<Resource<List<VacancyFunctTitle>>> =
        flow {
            if (textForSuggestions.length
                in MIN_VACANCY_SUGGESTION_REQUEST_TEXT_LENGTH..MAX_VACANCY_SUGGESTION_REQUEST_TEXT_LENGTH
            ) {
                val response = client.doRequest(
                    HeadHunterRequest.VacancySuggestions(
                        textForSuggestions
                    )
                )
                if (response.resultCode == Response.SUCCESS) {
                    emit(Resource.Success((response as VacancySuggestionsResponse).vacancyPositionsList))
                } else {
                    emit(Resource.Error(vacancySuggestionsErrorMessage))
                }
            } else {
                emit(Resource.Error(vacancySuggestionsRequestLengthErrorMessage))
            }
        }

    override suspend fun searchVacancy(textForSearch: String): Flow<Resource<VacancyListResponse>> = flow {
        val response = client.doRequest(
            HeadHunterRequest.VacancySearch(textForSearch)
        )
        if (response.resultCode == Response.SUCCESS) {
            emit(Resource.Success(response as VacancyListResponse))
        } else {
            emit(Resource.Error(vacancySearchErrorMessage))
        }
    }

    override suspend fun getVacancyById(id: String): Flow<Resource<VacancyDetails>> =
        flow {
            val response =
                client.doRequest(HeadHunterRequest.VacancyById(id))
            if (response.resultCode == Response.SUCCESS) {
                val data: VacancyByIdResponse = response as VacancyByIdResponse
                emit(Resource.Success(data.mapToDomain()))
            } else {
                emit(Resource.Error(vacancyGetByIdErrorMessage))
            }
        }
}
