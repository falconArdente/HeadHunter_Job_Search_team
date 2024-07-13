package ru.practicum.android.diploma.search.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.search.data.mapper.SearchVacancyConverter
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.domain.model.VacancyModelResponse
import ru.practicum.android.diploma.utils.Resource

class SearchInteractorImpl(val repository: SearchRepository, val converter: SearchVacancyConverter) : SearchInteractor {
    override fun searchVacancy(expression: String): Flow<Pair<VacancyModelResponse?, String?>> = flow {
        repository.searchVacancy(expression).map { result ->
            when (result) {
                is Resource.Success -> {
                    converter.mapToVacancyModelResponce(result.data!!)
                }

                is Resource.Error -> {
                    result.message
                }
            }
        }
    }
}
