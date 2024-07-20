package ru.practicum.android.diploma.network.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import ru.practicum.android.diploma.network.data.api.HeadHunterApplicationApi
import ru.practicum.android.diploma.network.data.api.HeadHunterNetworkClient
import ru.practicum.android.diploma.network.data.dto.HeadHunterRequest
import ru.practicum.android.diploma.network.data.dto.responses.AreasResponse
import ru.practicum.android.diploma.network.data.dto.responses.CountriesResponse
import ru.practicum.android.diploma.network.data.dto.responses.IndustryResponse
import ru.practicum.android.diploma.network.data.dto.responses.LocalesResponse
import ru.practicum.android.diploma.network.data.dto.responses.Response
import ru.practicum.android.diploma.network.data.dto.responses.VacancySuggestionsResponse
import ru.practicum.android.diploma.utils.NetworkStatus
import java.io.UncheckedIOException

class RetrofitBasedClient(retrofit: Retrofit, private val networkStatus: NetworkStatus) : HeadHunterNetworkClient {
    private val serverService = retrofit.create(HeadHunterApplicationApi::class.java)
    override suspend fun doRequest(request: HeadHunterRequest): Response {
        if (!networkStatus.isConnected()) return Response().apply { resultCode = -1 }
        return withContext(Dispatchers.IO) {
            try {
                val response = when (request) {
                    HeadHunterRequest.Locales -> LocalesResponse(
                        localeList = serverService.getLocales()
                    )

                    HeadHunterRequest.Dictionaries -> serverService.getDictionaries()
                    HeadHunterRequest.Industries -> IndustryResponse(
                        industriesList = serverService.getIndustries()
                    )

                    HeadHunterRequest.Areas -> AreasResponse(
                        areasList = serverService.getAreas()
                    )

                    HeadHunterRequest.Counties -> CountriesResponse(
                        countriesList = serverService.getCountries()
                    )

                    is HeadHunterRequest.VacancySuggestions -> VacancySuggestionsResponse(
                        vacancyPositionsList = serverService.getVacancySuggestions(
                            request.textForSuggestions
                        ).vacancyPositionsList
                    )

                    is HeadHunterRequest.VacancySearch -> serverService.searchVacancy(
                        textForSearch = request.textForSearch,
                        areaId = request.areaId,
                        industryIds = request.industryIds,
                        currencyCode = request.currencyCode,
                        salary = request.salary,
                        withSalaryOnly = request.withSalaryOnly,
                        page = request.page,
                        perPage = request.perPage
                    )

                    is HeadHunterRequest.VacancyById -> serverService.getVacancyById(request.id)
                }
                response.apply { resultCode = Response.SUCCESS }
            } catch (e: HttpException) {
                if (e.code() == Response.NOT_FOUND) {
                    Response().apply {
                        errorMessage = e.message
                        resultCode = Response.NOT_FOUND
                    }
                } else {
                    Response().apply {
                        errorMessage = e.message
                        resultCode = Response.FAILURE
                    }
                }
            } catch (e: UncheckedIOException) {
                Response().apply {
                    errorMessage = e.message
                    resultCode = Response.FAILURE
                }
            }
        }
    }
}
