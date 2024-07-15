package ru.practicum.android.diploma.search.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.network.data.dto.responses.VacancyListResponse
import ru.practicum.android.diploma.search.data.mapper.SearchVacancyConverter
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.search.domain.model.VacancyListResult
import ru.practicum.android.diploma.utils.Resource

class SearchInteractorImpl(val repository: SearchRepository, val converter: SearchVacancyConverter) : SearchInteractor {
    override fun searchVacancy(expression: String): Flow<VacancyListResult> = flow {
        Log.d("ROUTE__", "interactor $expression")
        Log.d("interactor SearchVac ", "щдщдщ")
        Log.d("interactor SearchVac ", expression)
        repository
            .searchVacancy(expression)
            .collect { vacancyListResponse->
                Log.d("interactor SearchVac Result ", vacancyListResponse.toString())
                when (vacancyListResponse) {
                    is Resource.Success -> {
                        Log.d("ROUTE__", "out repo ${(vacancyListResponse.data as VacancyListResponse).totalFound}")
                        Log.d("interactor SearchVac succes ", "щдщдщ")
                        emit(
                            VacancyListResult(
                                result = converter.mapToListVacancyModel(vacancyListResponse.data.vacancyAtSearchList),
                                errorMessage = null
                            )
                        )
                    }

                    is Resource.Error -> {
                        Log.d("interactor SearchVac error ", "щдщдщ")
                        VacancyListResult(null, vacancyListResponse.message)
                        emit(
                            VacancyListResult(
                                converter.mapToListVacancyModel(vacancyListResponse.data!!.vacancyAtSearchList),
                                null
                            )
                        )
                    }
                }
            }
    }
}
