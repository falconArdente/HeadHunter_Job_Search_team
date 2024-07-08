package ru.practicum.android.diploma.network.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import ru.practicum.android.diploma.App
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.network.dto.responses.LocaleResponse

interface HeadHunterApplicationApi {

    @Headers("User-Agent: ${App.USER_AGENT}", "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}")
    @GET("/locales")
    suspend fun getLocales(@Query("host") host: String = App.HOST): List<Locale>

    @Headers("User-Agent: ${App.USER_AGENT}", "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}")
    @GET("/industries")
    fun getIndustries(
        @Query("locale") locale: String = App.LOCALE,
        @Query("host") host: String = App.HOST
    ): List<LocaleResponse>
}
