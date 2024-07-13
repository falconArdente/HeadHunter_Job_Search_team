package ru.practicum.android.diploma.search.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.search.domain.model.VacancyListResult

interface SearchInteractor {
    fun searchVacancy(expression: String): Flow<VacancyListResult>
}
