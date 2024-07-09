package ru.practicum.android.diploma.search.data.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.network.dto.Area
import ru.practicum.android.diploma.network.dto.Country
import ru.practicum.android.diploma.network.dto.Industry
import ru.practicum.android.diploma.network.dto.Locale
import ru.practicum.android.diploma.network.dto.Skill
import ru.practicum.android.diploma.network.dto.responses.DictionariesResponse
import ru.practicum.android.diploma.utils.Resource

interface SearchRepository {
    suspend fun getLocales(): Flow<Resource<List<Locale>>>
    suspend fun getDictionaries(): Flow<Resource<DictionariesResponse>>
    suspend fun getIndustries(): Flow<Resource<List<Industry>>>
    suspend fun getAreas(): Flow<Resource<List<Area>>>
    suspend fun getCountries(): Flow<Resource<List<Country>>>
    suspend fun getSkillSuggestions(textForSuggestions: String): Flow<Resource<List<Skill>>>
}
