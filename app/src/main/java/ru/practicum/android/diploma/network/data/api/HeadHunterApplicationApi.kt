package ru.practicum.android.diploma.network.data.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import ru.practicum.android.diploma.App
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.network.data.dto.linked.Area
import ru.practicum.android.diploma.network.data.dto.linked.Country
import ru.practicum.android.diploma.network.data.dto.linked.Industry
import ru.practicum.android.diploma.network.data.dto.linked.Locale
import ru.practicum.android.diploma.network.data.dto.responses.DictionariesResponse
import ru.practicum.android.diploma.network.data.dto.responses.VacancyByIdResponse
import ru.practicum.android.diploma.network.data.dto.responses.VacancyListResponse
import ru.practicum.android.diploma.network.data.dto.responses.VacancySuggestionsResponse

interface HeadHunterApplicationApi {

    @Headers(
        "User-Agent: ${App.USER_AGENT}",
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}"
    )
    @GET("/locales")
    suspend fun getLocales(@Query("host") host: String = App.HOST): List<ru.practicum.android.diploma.network.data.dto.linked.Locale>

    @Headers(
        "User-Agent: ${App.USER_AGENT}",
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}"
    )
    @GET("/dictionaries")
    suspend fun getDictionaries(
        @Query("locale") locale: String = App.LOCALE,
        @Query("host") host: String = App.HOST
    ): ru.practicum.android.diploma.network.data.dto.responses.DictionariesResponse

    @Headers(
        "User-Agent: ${App.USER_AGENT}",
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}"
    )
    @GET("/industries")
    suspend fun getIndustries(
        @Query("locale") locale: String = App.LOCALE,
        @Query("host") host: String = App.HOST
    ): List<ru.practicum.android.diploma.network.data.dto.linked.Industry>

    @Headers(
        "User-Agent: ${App.USER_AGENT}",
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}"
    )
    @GET("/areas")
    suspend fun getAreas(
        @Query("locale") locale: String = App.LOCALE,
        @Query("host") host: String = App.HOST
    ): List<ru.practicum.android.diploma.network.data.dto.linked.Area>

    @Headers(
        "User-Agent: ${App.USER_AGENT}",
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}"
    )
    @GET("/areas/countries")
    suspend fun getCountries(
        @Query("locale") locale: String = App.LOCALE,
        @Query("host") host: String = App.HOST
    ): List<ru.practicum.android.diploma.network.data.dto.linked.Country>

    @Headers(
        "User-Agent: ${App.USER_AGENT}",
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}"
    )
    @GET("/suggests/vacancy_positions")
    suspend fun getVacancySuggestions(
        @Query("text") textForSuggestions: String,
        @Query("locale") locale: String = App.LOCALE,
        @Query("host") host: String = App.HOST
    ): ru.practicum.android.diploma.network.data.dto.responses.VacancySuggestionsResponse

    @Headers(
        "User-Agent: ${App.USER_AGENT}",
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}"
    )
    @GET("/vacancies")
    suspend fun searchVacancy(
        @Query("text") textForSearch: String,
        @Query("locale") locale: String = App.LOCALE,
        @Query("host") host: String = App.HOST
    ): ru.practicum.android.diploma.network.data.dto.responses.VacancyListResponse

    @Headers(
        "User-Agent: ${App.USER_AGENT}",
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}"
    )
    @GET("/vacancies/{vacancy_id}")
    suspend fun getVacancyById(
        @Path("vacancy_id") id: String,
        @Query("locale") locale: String = App.LOCALE,
        @Query("host") host: String = App.HOST
    ): ru.practicum.android.diploma.network.data.dto.responses.VacancyByIdResponse
}
