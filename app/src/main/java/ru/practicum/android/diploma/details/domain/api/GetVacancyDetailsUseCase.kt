package ru.practicum.android.diploma.details.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.details.domain.model.VacancyDetails
import ru.practicum.android.diploma.utils.Resource

interface GetVacancyDetailsUseCase {
    suspend fun execute(id: String): Flow<Resource<VacancyDetails>>
}
