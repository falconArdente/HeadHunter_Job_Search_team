package ru.practicum.android.diploma.search.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.model.SearchParameters
import ru.practicum.android.diploma.search.domain.model.VacancyListResult

interface SearchInteractor {
    fun searchVacancy(
        expression: String,
        parameters: SearchParameters?,
        perPage: Int,
        currentPage: Int
    ): Flow<VacancyListResult>
}
