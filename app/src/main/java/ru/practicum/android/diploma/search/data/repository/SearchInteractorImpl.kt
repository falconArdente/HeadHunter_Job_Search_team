package ru.practicum.android.diploma.search.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.search.data.mapper.SearchVacancyConverter
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.domain.model.SearchParameters
import ru.practicum.android.diploma.search.domain.model.VacancyListResult
import ru.practicum.android.diploma.utils.Resource

class SearchInteractorImpl(val repository: SearchRepository, val converter: SearchVacancyConverter) : SearchInteractor {
    override fun searchVacancy(expression: String, parameters: SearchParameters?): Flow<VacancyListResult> = flow {
        var area: String? = null
        var industry: List<String>? = null
        var salary_: Int? = null
        var salaryOnly = false
        if (parameters != null) {
            with(parameters) {
                area = areaId
                industry = industryIds
                salary_ = salary
                salaryOnly = withSalaryOnly
            }
        }
        repository
            .searchVacancy(
                textForSearch = expression,
                areaId = area,
                industryIds = industry,
                salary = salary_,
                withSalaryOnly = salaryOnly,
            )
            .collect { vacancyListResponse ->
                when (vacancyListResponse) {
                    is Resource.Success -> {
                        val vacancyList =
                            converter.mapToListVacancyModel(vacancyListResponse.data!!.vacancyAtSearchList)
                        val foundVacancy = vacancyListResponse.data.totalFound
                        val pagesTotal = vacancyListResponse.data.totalPages
                        emit(
                            VacancyListResult(
                                result = vacancyList,
                                errorMessage = "",
                                foundVacancy = foundVacancy.toInt(),
                                page = 0,
                                pages = pagesTotal.toInt(),
                            )
                        )
                    }

                    is Resource.Error, is Resource.InternetConnectionError, is Resource.NotFoundError -> {
                        emit(
                            VacancyListResult(emptyList(), vacancyListResponse.message, 0, 0, 0)
                        )
                    }
                }
            }
    }
}
