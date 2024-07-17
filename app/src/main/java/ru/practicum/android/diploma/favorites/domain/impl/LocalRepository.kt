package ru.practicum.android.diploma.favorites.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.details.domain.model.VacancyDetails
import ru.practicum.android.diploma.utils.Resource

interface LocalRepository {
    suspend fun getFavoritesVacancyList(): Flow<Resource<List<VacancyDetails>>>
}
