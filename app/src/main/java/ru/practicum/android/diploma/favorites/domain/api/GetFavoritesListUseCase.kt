package ru.practicum.android.diploma.favorites.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.details.domain.model.VacancyDetails
import ru.practicum.android.diploma.utils.Resource

fun interface GetFavoritesListUseCase {
    suspend fun execute(): Flow<Resource<List<VacancyDetails>>>
}
