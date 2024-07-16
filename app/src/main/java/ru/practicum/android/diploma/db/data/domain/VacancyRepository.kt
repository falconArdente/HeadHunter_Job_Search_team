package ru.practicum.android.diploma.db.data.domain

import ru.practicum.android.diploma.details.domain.model.VacancyDetails

interface VacancyRepository {

    suspend fun isExistsVacancy(vacancyId: Int) :Boolean

    suspend fun getAllVacancy(): List<VacancyDetails>

    suspend fun getAllVacancyByPage(pageNum: Int): List<VacancyDetails>

    suspend fun insertVacancy(vacancyDetails: VacancyDetails)

    suspend fun deleteVacancy(vacancyId: Int)

    suspend fun insertVacancyWitCheck(vacancyDetails: VacancyDetails)
}
