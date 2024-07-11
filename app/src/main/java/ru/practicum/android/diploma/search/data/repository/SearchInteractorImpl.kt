package ru.practicum.android.diploma.search.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.utils.Resource
import ru.practicum.android.diploma.search.domain.model.VacancyModelResponce as VacancyModelResponce1

class SearchInteractorImpl(val repository: SearchRepository, val converter: SearchVacancyConverter) : SearchInteractor {
    override fun searchVacation(expression: String): Flow<Pair<VacancyModelResponce1?, String?>> = flow {
        repository.searchVacancy(expression).map { result ->
            when (result) {
                is Resource.Success -> {
                    Pair(converter.mapToVacancyModelResponce(result.data!!), null)
                }

                is Resource.Error -> {
                    Pair(null, result.message)
                }
            }
        }
    }
}
