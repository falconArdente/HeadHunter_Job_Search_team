package ru.practicum.android.diploma.search.data.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.network.data.dto.linked.AreaDTO
import ru.practicum.android.diploma.network.data.dto.linked.CountryDTO
import ru.practicum.android.diploma.network.data.dto.linked.IndustryDTO
import ru.practicum.android.diploma.network.data.dto.linked.Locale
import ru.practicum.android.diploma.network.data.dto.linked.VacancyFunctTitle
import ru.practicum.android.diploma.network.data.dto.responses.DictionariesResponse
import ru.practicum.android.diploma.network.data.dto.responses.VacancyListResponse
import ru.practicum.android.diploma.utils.Resource

interface SearchRepository {
    suspend fun getLocales(): Flow<Resource<List<Locale>>>
    suspend fun getDictionaries(): Flow<Resource<DictionariesResponse>>
    suspend fun getIndustries(): Flow<Resource<List<IndustryDTO>>>
    suspend fun getAreas(): Flow<Resource<List<AreaDTO>>>
    suspend fun getCountries(): Flow<Resource<List<CountryDTO>>>
    suspend fun getVacancySuggestions(textForSuggestions: String): Flow<Resource<List<VacancyFunctTitle>>>
    suspend fun searchVacancy(textForSearch: String): Flow<Resource<VacancyListResponse>>
}
