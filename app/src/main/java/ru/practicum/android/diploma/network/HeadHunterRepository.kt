package ru.practicum.android.diploma.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.network.api.Locale
import ru.practicum.android.diploma.network.dto.HeadHunterRequest
import ru.practicum.android.diploma.network.dto.responses.LocaleResponse
import ru.practicum.android.diploma.utils.Resource

const val BASE_URL = "https://api.hh.ru"

class HeadHunterRepository {
    private val client = RetrofitBasedClient()

    suspend fun getLocales(): Flow<Resource<List<Locale>>> = flow {
        val response = client.doRequest(HeadHunterRequest.LocalesList)
        if (response.resultCode == 200) {
            emit(Resource.Success((response as LocaleResponse).localeList))
        } else {
            emit(Resource.Error(""))
        }
    }

}
