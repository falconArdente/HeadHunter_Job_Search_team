package ru.practicum.android.diploma.favorites.data.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.details.domain.model.VacancyDetails
import ru.practicum.android.diploma.favorites.domain.impl.LocalRepository
import ru.practicum.android.diploma.utils.Resource

class FavoritesRepositoryRoomBased():LocalRepository {
    override suspend fun getFavoritesVacancyList(): Flow<Resource<List<VacancyDetails>>> {
        TODO("Not yet implemented")
    }
}
