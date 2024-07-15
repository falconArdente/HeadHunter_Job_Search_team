package ru.practicum.android.diploma.db.data.impl

import ru.practicum.android.diploma.db.data.db.AppDatabase
import ru.practicum.android.diploma.db.data.db.VacancyDbConvertor
import ru.practicum.android.diploma.db.data.domain.VacancyRepository

class VacancyRepositoryImpl(
    private val appDatabase:  AppDatabase,
    private val vacancyDbConvertor: VacancyDbConvertor
): VacancyRepository {
    override suspend fun isExistsVacancy(vacancyId: Int): Boolean {
        return appDatabase.vacancyDao().isExistsVacancy(vacancyId) != null
    }
}
