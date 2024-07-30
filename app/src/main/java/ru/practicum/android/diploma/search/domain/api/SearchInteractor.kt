package ru.practicum.android.diploma.search.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.model.SearchParameters
import ru.practicum.android.diploma.search.domain.model.VacancyListResult
import ru.practicum.android.diploma.utils.Resource

interface SearchInteractor {
    fun searchVacancy(
        expression: String,
        parameters: SearchParameters?,
        perPage: Int,
        currentPage: Int
    ): Flow<Resource<VacancyListResult>>
}
