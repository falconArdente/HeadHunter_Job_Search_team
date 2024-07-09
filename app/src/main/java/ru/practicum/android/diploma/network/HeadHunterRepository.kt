package ru.practicum.android.diploma.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.network.api.HeadHunterNetworkClient
import ru.practicum.android.diploma.network.dto.HeadHunterRequest
import ru.practicum.android.diploma.network.dto.Industry
import ru.practicum.android.diploma.network.dto.Locale
import ru.practicum.android.diploma.network.dto.responses.DictionariesResponse
import ru.practicum.android.diploma.network.dto.responses.IndustryResponse
import ru.practicum.android.diploma.network.dto.responses.LocalesResponse
import ru.practicum.android.diploma.search.data.repository.SearchRepository
import ru.practicum.android.diploma.utils.Resource

class HeadHunterRepository(private val client: HeadHunterNetworkClient) : SearchRepository {

    override suspend fun getLocales(): Flow<Resource<List<Locale>>> = flow {
        val response = client.doRequest(HeadHunterRequest.Locales)
        if (response.resultCode == 200) {
            emit(Resource.Success((response as LocalesResponse).localeList))
        } else {
            emit(Resource.Error(""))
        }
    }

    override suspend fun getDictionaries(): Flow<Resource<DictionariesResponse>> = flow {
        val response = client.doRequest(HeadHunterRequest.Dictionaries)
        if (response.resultCode == 200) {
            emit(Resource.Success(response as DictionariesResponse))
        } else {
            emit(Resource.Error("dictionary error"))
        }
    }

    override suspend fun getIndustries(): Flow<Resource<List<Industry>>> = flow {
        val response = client.doRequest(HeadHunterRequest.Industries)
        if (response.resultCode == 200) {
            emit(Resource.Success((response as IndustryResponse).industriesList))
        } else {
            emit(Resource.Error("industries error"))
        }
    }
}
