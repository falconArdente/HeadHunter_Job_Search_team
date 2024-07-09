package ru.practicum.android.diploma.network.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import ru.practicum.android.diploma.App
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.network.dto.Industry
import ru.practicum.android.diploma.network.dto.Locale
import ru.practicum.android.diploma.network.dto.responses.DictionariesResponse

interface HeadHunterApplicationApi {

    @Headers("User-Agent: ${App.USER_AGENT}", "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}")
    @GET("/locales")
    suspend fun getLocales(@Query("host") host: String = App.HOST): List<Locale>

    @Headers("User-Agent: ${App.USER_AGENT}", "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}")
    @GET("/dictionaries")
    suspend fun getDictionaries(
        @Query("locale") locale: String = App.LOCALE, @Query("host") host: String = App.HOST
    ): DictionariesResponse

    @Headers("User-Agent: ${App.USER_AGENT}", "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}")
    @GET("/industries")
    suspend fun getIndustries(
        @Query("locale") locale: String = App.LOCALE, @Query("host") host: String = App.HOST
    ): List<Industry>
}
