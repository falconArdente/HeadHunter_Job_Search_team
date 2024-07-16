package ru.practicum.android.diploma.db.data.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.db.data.db.AppDatabase
import ru.practicum.android.diploma.db.data.db.VacancyDbConvertor
import ru.practicum.android.diploma.db.domain.api.VacancyRepository
import ru.practicum.android.diploma.details.domain.model.VacancyDetails

class VacancyRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val vacancyDbConvertor: VacancyDbConvertor
) : VacancyRepository {

    override fun isExistsVacancy(vacancyId: Int): Flow<Boolean> = flow {
        emit(appDatabase.vacancyDao().isExistsVacancy(vacancyId) != null)
    }

    override fun getVacancyById(vacancyId: Int): Flow<VacancyDetails?> = flow {
        val vacancy = appDatabase.vacancyDao().getVacancyById(vacancyId)
        if (vacancy != null)
            emit(vacancyDbConvertor.mapVacancy(vacancy))
        else emit(null)
    }

    override fun getAllVacancy(): Flow<List<VacancyDetails>> = flow {
        val listVacancy = appDatabase.vacancyDao().getAllVacancy()
        emit(listVacancy.map { vacancy -> vacancyDbConvertor.mapVacancy(vacancy) })
    }

    override fun getAllVacancyByPage(pageNum: Int): Flow<List<VacancyDetails>> = flow {
        val listVacancy = appDatabase.vacancyDao().getAllVacancyByPage(pageNum)
        emit(listVacancy.map { vacancy -> vacancyDbConvertor.mapVacancy(vacancy) })
    }

    override suspend fun insertVacancy(vacancyDetails: VacancyDetails) {
        val vacancyJoins = vacancyDbConvertor.mapVacancy(vacancyDetails)
        appDatabase.vacancyDao().insertVacancyJoins(vacancyJoins)
    }

    override suspend fun deleteVacancy(vacancyId: Int) {
        val vacancy = appDatabase.vacancyDao().getVacancyById(vacancyId)
        if (vacancy != null) {
            appDatabase.vacancyDao().deleteVacancyJoins(vacancy)
        }
    }

    override suspend fun insertVacancyWitCheck(vacancyDetails: VacancyDetails) {
        deleteVacancy(vacancyDetails.id.toInt())
        val vacancyJoins = vacancyDbConvertor.mapVacancy(vacancyDetails)
        appDatabase.vacancyDao().insertVacancyJoins(vacancyJoins)
    }

}
