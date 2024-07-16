package ru.practicum.android.diploma.search.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.search.data.mapper.SearchVacancyConverter
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.domain.model.VacancyListResult
import ru.practicum.android.diploma.utils.Resource

class SearchInteractorImpl(val repository: SearchRepository, val converter: SearchVacancyConverter) : SearchInteractor {
    override fun searchVacancy(expression: String): Flow<VacancyListResult> = flow {
        repository
            .searchVacancy(expression)
            .collect { vacancyListResponse ->
                when (vacancyListResponse) {
                    is Resource.Success -> {
                        val vacancyList=converter.mapToListVacancyModel(vacancyListResponse.data!!.vacancyAtSearchList)
                        emit(
                            VacancyListResult(
                                result = vacancyList,
                                errorMessage = ""
                            )
                        )
                    }

                    is Resource.Error -> {
                        emit(
                            VacancyListResult(emptyList(), vacancyListResponse.message)
                        )
                    }
                }
            }
    }
}
