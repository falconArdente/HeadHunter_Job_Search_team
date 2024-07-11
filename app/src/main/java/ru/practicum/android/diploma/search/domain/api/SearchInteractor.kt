package ru.practicum.android.diploma.search.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.model.VacancyModelResponce

interface SearchInteractor {
    fun searchVacation(expression: String): Flow<Pair<VacancyModelResponce?, String?>>
}
