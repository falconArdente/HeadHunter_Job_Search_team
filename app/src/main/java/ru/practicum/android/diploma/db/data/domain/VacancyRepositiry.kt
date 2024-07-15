package ru.practicum.android.diploma.db.data.domain

interface VacancyRepositiry {
    suspend fun isExistsVacancy(vacancyId: Int) :Boolean
}
