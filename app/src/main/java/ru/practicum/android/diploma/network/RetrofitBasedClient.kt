package ru.practicum.android.diploma.network


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.network.api.HeadHunterApplicationApi
import ru.practicum.android.diploma.network.api.HeadHunterNetworkClient
import ru.practicum.android.diploma.network.dto.HeadHunterRequest
import ru.practicum.android.diploma.network.dto.responses.LocaleResponse
import ru.practicum.android.diploma.network.dto.responses.Response

class RetrofitBasedClient() : HeadHunterNetworkClient {
    private val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpDefaultClient())
            .build()
    private val serverService = retrofit.create(HeadHunterApplicationApi::class.java)
    override suspend fun doRequest(request: HeadHunterRequest): Response {
        // if (request !is HeadHunterRequest) return Response().apply { resultCode = -1 }
        return withContext(Dispatchers.IO) {
            try {
                when (request) {
                    is HeadHunterRequest.LocalesList -> {
                        val response = LocaleResponse(localeList = serverService.getLocales())
                        response.apply { resultCode = 200 }
                    }
                }
            } catch (e: Throwable) {
                Response().apply { resultCode = 500 }
            }
        }
    }

    private fun createOkHttpDefaultClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()
    }
}
