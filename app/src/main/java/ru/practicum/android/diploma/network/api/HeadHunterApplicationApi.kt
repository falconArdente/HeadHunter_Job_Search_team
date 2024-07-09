package ru.practicum.android.diploma.network.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import ru.practicum.android.diploma.App
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.network.dto.linked_classes.Area
import ru.practicum.android.diploma.network.dto.linked_classes.Country
import ru.practicum.android.diploma.network.dto.linked_classes.Industry
import ru.practicum.android.diploma.network.dto.linked_classes.Locale
import ru.practicum.android.diploma.network.dto.responses.DictionariesResponse
import ru.practicum.android.diploma.network.dto.responses.VacancyByIdResponse
import ru.practicum.android.diploma.network.dto.responses.VacancyListResponse
import ru.practicum.android.diploma.network.dto.responses.VacancySuggestionsResponse

interface HeadHunterApplicationApi {

    @Headers("User-Agent: ${App.USER_AGENT}", "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}")
    @GET("/locales")
    suspend fun getLocales(@Query("host") host: String = App.HOST): List<Locale>

    @Headers("User-Agent: ${App.USER_AGENT}", "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}")
    @GET("/dictionaries")
    suspend fun getDictionaries(
        @Query("locale") locale: String = App.LOCALE,
        @Query("host") host: String = App.HOST
    ): DictionariesResponse

    @Headers("User-Agent: ${App.USER_AGENT}", "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}")
    @GET("/industries")
    suspend fun getIndustries(
        @Query("locale") locale: String = App.LOCALE,
        @Query("host") host: String = App.HOST
    ): List<Industry>

    @Headers("User-Agent: ${App.USER_AGENT}", "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}")
    @GET("/areas")
    suspend fun getAreas(
        @Query("locale") locale: String = App.LOCALE,
        @Query("host") host: String = App.HOST
    ): List<Area>

    @Headers("User-Agent: ${App.USER_AGENT}", "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}")
    @GET("/areas/countries")
    suspend fun getCountries(
        @Query("locale") locale: String = App.LOCALE,
        @Query("host") host: String = App.HOST
    ): List<Country>

    @Headers("User-Agent: ${App.USER_AGENT}", "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}")
    @GET("/suggests/vacancy_positions")
    suspend fun getVacancySuggestions(
        @Query("text") textForSuggestions: String,
        @Query("locale") locale: String = App.LOCALE,
        @Query("host") host: String = App.HOST
    ): VacancySuggestionsResponse

    @Headers("User-Agent: ${App.USER_AGENT}", "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}")
    @GET("/vacancies")
    suspend fun searchVacancy(
        @Query("text") textForSearch: String,
        @Query("locale") locale: String = App.LOCALE,
        @Query("host") host: String = App.HOST
    ): VacancyListResponse

    @Headers("User-Agent: ${App.USER_AGENT}", "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}")
    @GET("/vacancies/{vacancy_id}")
    suspend fun getVacancyById(
        @Path("vacancy_id") id: String,
        @Query("locale") locale: String = App.LOCALE,
        @Query("host") host: String = App.HOST
    ): VacancyByIdResponse
}
