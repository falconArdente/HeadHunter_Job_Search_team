package ru.practicum.android.diploma.db.data.domain

interface VacancyRepository {
    suspend fun isExistsVacancy(vacancyId: Int) :Boolean
}
