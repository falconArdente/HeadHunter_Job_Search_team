package ru.practicum.android.diploma.search.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.model.VacancyModelResponse

interface SearchInteractor {
    fun searchVacancy(expression: String): Flow<Pair<VacancyModelResponse?, String?>>
}
