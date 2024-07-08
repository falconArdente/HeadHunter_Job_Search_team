package ru.practicum.android.diploma.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.network.api.HeadHunterApplicationApi
import ru.practicum.android.diploma.network.api.Locale

const val BASE_URL = "https://api.hh.ru"

class HeadHunterRepository {

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpDefaultClient())
            .build()
    private val serverService = retrofit.create(HeadHunterApplicationApi::class.java)
    suspend fun getLocales(): List<Locale> {
        return serverService.getLocales()
    }

    private fun createOkHttpDefaultClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()
    }
}
