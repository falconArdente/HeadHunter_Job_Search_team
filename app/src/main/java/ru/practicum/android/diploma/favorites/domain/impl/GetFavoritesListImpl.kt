package ru.practicum.android.diploma.favorites.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.details.domain.model.VacancyDetails
import ru.practicum.android.diploma.favorites.domain.api.GetFavoritesListUseCase
import ru.practicum.android.diploma.utils.Resource

class GetFavoritesListImpl(private val repository: LocalRepository) : GetFavoritesListUseCase {
    override suspend fun execute(): Flow<Resource<List<VacancyDetails>>> {
        return repository.getFavoritesVacancyList()
    }
}
