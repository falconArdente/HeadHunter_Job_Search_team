package ru.practicum.android.diploma.db.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.details.domain.model.VacancyDetails

interface VacancyRepository {

    fun isExistsVacancy(vacancyId: Int): Flow<Boolean>

    fun getVacancyById(vacancyId: Int): Flow<VacancyDetails?>

    fun getAllVacancy(): Flow<List<VacancyDetails>>

    fun getAllVacancyByPage(pageNum: Int): Flow<List<VacancyDetails>>

    suspend fun insertVacancy(vacancyDetails: VacancyDetails)

    suspend fun deleteVacancy(vacancyId: Int)

    suspend fun insertVacancyWithCheck(vacancyDetails: VacancyDetails)
}
