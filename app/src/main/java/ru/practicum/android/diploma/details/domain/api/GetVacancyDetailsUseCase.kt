package ru.practicum.android.diploma.details.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.details.domain.model.VacancyDetails
import ru.practicum.android.diploma.utils.Resource

fun interface GetVacancyDetailsUseCase {
    suspend fun execute(id: String): Flow<Resource<VacancyDetails>>
}
