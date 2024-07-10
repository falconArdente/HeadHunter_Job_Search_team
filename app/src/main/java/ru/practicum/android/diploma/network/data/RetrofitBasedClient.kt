package ru.practicum.android.diploma.network.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import ru.practicum.android.diploma.network.data.api.HeadHunterApplicationApi
import ru.practicum.android.diploma.network.data.api.HeadHunterNetworkClient
import java.io.UncheckedIOException

class RetrofitBasedClient(retrofit: Retrofit) : HeadHunterNetworkClient {
    private val serverService = retrofit.create(HeadHunterApplicationApi::class.java)
    override suspend fun doRequest(request: ru.practicum.android.diploma.network.data.dto.HeadHunterRequest): ru.practicum.android.diploma.network.data.dto.responses.Response {
        // if (request !is HeadHunterRequest) return Response().apply { resultCode = -1 }
        return withContext(Dispatchers.IO) {
            try {
                val response = when (request) {
                    ru.practicum.android.diploma.network.data.dto.HeadHunterRequest.Locales -> ru.practicum.android.diploma.network.data.dto.responses.LocalesResponse(
                        localeList = serverService.getLocales()
                    )
                    ru.practicum.android.diploma.network.data.dto.HeadHunterRequest.Dictionaries -> serverService.getDictionaries()
                    ru.practicum.android.diploma.network.data.dto.HeadHunterRequest.Industries -> ru.practicum.android.diploma.network.data.dto.responses.IndustryResponse(
                        industriesList = serverService.getIndustries()
                    )
                    ru.practicum.android.diploma.network.data.dto.HeadHunterRequest.Areas -> ru.practicum.android.diploma.network.data.dto.responses.AreasResponse(
                        areasList = serverService.getAreas()
                    )
                    ru.practicum.android.diploma.network.data.dto.HeadHunterRequest.Counties -> ru.practicum.android.diploma.network.data.dto.responses.CountriesResponse(
                        countriesList = serverService.getCountries()
                    )
                    is ru.practicum.android.diploma.network.data.dto.HeadHunterRequest.VacancySuggestions -> ru.practicum.android.diploma.network.data.dto.responses.VacancySuggestionsResponse(
                        vacancyPositionsList = serverService.getVacancySuggestions(
                            request.textForSuggestions
                        ).vacancyPositionsList
                    )

                    is ru.practicum.android.diploma.network.data.dto.HeadHunterRequest.VacancySearch -> serverService.searchVacancy(request.textForSearch)
                    is ru.practicum.android.diploma.network.data.dto.HeadHunterRequest.VacancyById -> serverService.getVacancyById(request.id)
                }
                response.apply { resultCode = ru.practicum.android.diploma.network.data.dto.responses.Response.SUCCESS }
            } catch (e: UncheckedIOException) {
                ru.practicum.android.diploma.network.data.dto.responses.Response().apply {
                    errorMessage = e.message
                    resultCode = ru.practicum.android.diploma.network.data.dto.responses.Response.FAILURE
                }
            }
        }
    }
}
