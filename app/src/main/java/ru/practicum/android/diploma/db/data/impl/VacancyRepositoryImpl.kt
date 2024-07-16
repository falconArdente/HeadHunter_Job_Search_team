package ru.practicum.android.diploma.db.data.impl

import ru.practicum.android.diploma.db.data.db.AppDatabase
import ru.practicum.android.diploma.db.data.db.VacancyDbConvertor
import ru.practicum.android.diploma.db.data.domain.VacancyRepository
import ru.practicum.android.diploma.details.domain.model.VacancyDetails

class VacancyRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val vacancyDbConvertor: VacancyDbConvertor
) : VacancyRepository {

    override suspend fun isExistsVacancy(vacancyId: Int): Boolean {
        return appDatabase.vacancyDao().isExistsVacancy(vacancyId) != null
    }

    override suspend fun getAllVacancy(): List<VacancyDetails> {
        val listVacancy = appDatabase.vacancyDao().getAllVacancy()
        return listVacancy.map { vacancy -> vacancyDbConvertor.mapVacancy(vacancy) }
    }

    override suspend fun getAllVacancyByPage(pageNum: Int): List<VacancyDetails> {
        val listVacancy = appDatabase.vacancyDao().getAllVacancyByPage(pageNum)
        return listVacancy.map { vacancy -> vacancyDbConvertor.mapVacancy(vacancy) }
    }

    override suspend fun insertVacancy(vacancyDetails: VacancyDetails) {
        val vacancyJoins = vacancyDbConvertor.mapVacancy(vacancyDetails)
        appDatabase.vacancyDao().insertVacancyJoins(vacancyJoins)
    }

    override suspend fun deleteVacancy(vacancyId: Int) {
        val vacancy = appDatabase.vacancyDao().getVacancyById(vacancyId)
        if (vacancy != null) {
            val vacancyJoins = appDatabase.vacancyDao().getVacancyById(vacancyId)
            appDatabase.vacancyDao().deleteVacancy(vacancyJoins!!)
        }
    }

    override suspend fun insertVacancyWitCheck(vacancyDetails: VacancyDetails) {
        deleteVacancy(vacancyDetails.id.toInt())
        val vacancyJoins = vacancyDbConvertor.mapVacancy(vacancyDetails)
        appDatabase.vacancyDao().insertVacancyJoins(vacancyJoins)
    }

}
