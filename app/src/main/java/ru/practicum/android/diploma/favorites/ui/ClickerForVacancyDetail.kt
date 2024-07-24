package ru.practicum.android.diploma.favorites.ui

import ru.practicum.android.diploma.details.domain.model.VacancyDetails

fun interface ClickerForVacancyDetail {
    fun onItemClick(vacancy: VacancyDetails)
}
