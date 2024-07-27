package ru.practicum.android.diploma.favorites.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.impl.VacancyRepository
import ru.practicum.android.diploma.details.domain.model.VacancyDetails
import ru.practicum.android.diploma.favorites.domain.api.FavoriteDbInteractor

class FavoriteDbInteractorImpl(private val vacancyRepository: VacancyRepository) : FavoriteDbInteractor {
    override fun getAllVacancy(): Flow<List<VacancyDetails>> {
        return vacancyRepository.getAllVacancy()
    }

    override suspend fun deleteVacancy(vacancyId: Int) {
        vacancyRepository.deleteVacancy(vacancyId)
    }

    override fun getAllVacancyByPage(pageNum: Int): Flow<List<VacancyDetails>> {
        return vacancyRepository.getAllVacancyByPage(pageNum)
    }

    override suspend fun insertVacancy(vacancyDetails: VacancyDetails) {
        vacancyRepository.insertVacancy(vacancyDetails)
    }

    override suspend fun insertVacancyWitCheck(vacancyDetails: VacancyDetails) {
        vacancyRepository.insertVacancyWithCheck(vacancyDetails)
    }

    override fun isExistsVacancy(vacancyId: Int): Flow<Boolean> {
        return vacancyRepository.isExistsVacancy(vacancyId)
    }
}
