package ru.practicum.android.diploma.search.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.search.data.mapper.SearchVacancyConverter
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.domain.model.VacancyListResult
import ru.practicum.android.diploma.utils.Resource

class SearchInteractorImpl(val repository: SearchRepository, val converter: SearchVacancyConverter) : SearchInteractor {
    override fun searchVacancy(expression: String): Flow<VacancyListResult> = flow {
        Log.d("interactor SearchVac ", expression)
        repository.searchVacancy(expression).map { result ->
            Log.d("interactor SearchVac Result ", result.toString())
            when (result) {
                is Resource.Success -> {
                    Log.d("interactor SearchVac success ", "щдщдщ")
                    VacancyListResult(converter.mapToListVacancyModel(result.data!!.vacancyAtSearchList), null)
                }

                is Resource.Error -> {
                    Log.d("interactor SearchVac error ", "щдщдщ")
                    VacancyListResult(null, result.message)
                }
            }
        }
    }
}
