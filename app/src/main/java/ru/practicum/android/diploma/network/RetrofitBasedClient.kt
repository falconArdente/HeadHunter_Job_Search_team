package ru.practicum.android.diploma.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import ru.practicum.android.diploma.network.api.HeadHunterApplicationApi
import ru.practicum.android.diploma.network.api.HeadHunterNetworkClient
import ru.practicum.android.diploma.network.dto.HeadHunterRequest
import ru.practicum.android.diploma.network.dto.responses.IndustryResponse
import ru.practicum.android.diploma.network.dto.responses.LocalesResponse
import ru.practicum.android.diploma.network.dto.responses.Response

const val SUCCESS = 200
const val FAILURE = 500

class RetrofitBasedClient(retrofit: Retrofit) : HeadHunterNetworkClient {

    private val serverService = retrofit.create(HeadHunterApplicationApi::class.java)
    override suspend fun doRequest(request: HeadHunterRequest): Response {
        // if (request !is HeadHunterRequest) return Response().apply { resultCode = -1 }
        return withContext(Dispatchers.IO) {
            try {
                when (request) {
                    is HeadHunterRequest.Locales -> {
                        val response = LocalesResponse(localeList = serverService.getLocales())
                        response.apply { resultCode = SUCCESS }
                    }

                    HeadHunterRequest.Dictionaries -> {
                        val response = serverService.getDictionaries()
                        response.apply { resultCode = SUCCESS }
                    }

                    HeadHunterRequest.Industries -> {
                        val response = IndustryResponse(industriesList = serverService.getIndustries())
                        response.apply { resultCode = SUCCESS }
                    }
                }
            } catch (e: Throwable) {
                Response().apply { resultCode = FAILURE }
            }
        }
    }
}

