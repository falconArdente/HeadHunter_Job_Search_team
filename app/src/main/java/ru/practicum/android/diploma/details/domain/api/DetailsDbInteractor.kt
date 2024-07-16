package ru.practicum.android.diploma.details.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.details.domain.model.VacancyDetails

interface DetailsDbInteractor {
    fun isExistsVacancy(vacancyId: Int): Flow<Boolean>

    suspend fun insertVacancy(vacancyDetails: VacancyDetails)

    suspend fun deleteVacancy(vacancyId: Int)

    suspend fun insertVacancyWitCheck(vacancyDetails: VacancyDetails)

    fun getVacancyById(vacancyId: Int): Flow<VacancyDetails?>
}
