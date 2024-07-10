package ru.practicum.android.diploma.search.data.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.network.data.dto.linked.Area
import ru.practicum.android.diploma.network.data.dto.linked.Country
import ru.practicum.android.diploma.network.data.dto.linked.Industry
import ru.practicum.android.diploma.network.data.dto.linked.Locale
import ru.practicum.android.diploma.network.data.dto.linked.VacancyFunctTitle
import ru.practicum.android.diploma.network.data.dto.responses.DictionariesResponse
import ru.practicum.android.diploma.network.data.dto.responses.VacancyByIdResponse
import ru.practicum.android.diploma.network.data.dto.responses.VacancyListResponse
import ru.practicum.android.diploma.utils.Resource

interface SearchRepository {
    suspend fun getLocales(): Flow<Resource<List<ru.practicum.android.diploma.network.data.dto.linked.Locale>>>
    suspend fun getDictionaries(): Flow<Resource<ru.practicum.android.diploma.network.data.dto.responses.DictionariesResponse>>
    suspend fun getIndustries(): Flow<Resource<List<ru.practicum.android.diploma.network.data.dto.linked.Industry>>>
    suspend fun getAreas(): Flow<Resource<List<ru.practicum.android.diploma.network.data.dto.linked.Area>>>
    suspend fun getCountries(): Flow<Resource<List<ru.practicum.android.diploma.network.data.dto.linked.Country>>>
    suspend fun getVacancySuggestions(textForSuggestions: String): Flow<Resource<List<ru.practicum.android.diploma.network.data.dto.linked.VacancyFunctTitle>>>
    suspend fun searchVacancy(textForSearch: String): Flow<Resource<ru.practicum.android.diploma.network.data.dto.responses.VacancyListResponse>>
    suspend fun getVacancyById(id: String): Flow<Resource<ru.practicum.android.diploma.network.data.dto.responses.VacancyByIdResponse>>
}
