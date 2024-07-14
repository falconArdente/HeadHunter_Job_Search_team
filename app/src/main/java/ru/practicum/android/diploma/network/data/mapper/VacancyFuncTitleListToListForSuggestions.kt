package ru.practicum.android.diploma.network.data.mapper

import ru.practicum.android.diploma.network.data.dto.linked.VacancyFunctTitle

fun vacancyFuncTitleListToListForSuggestions(listOfVacancyTitles: List<VacancyFunctTitle>): List<String> {
    val listOfSuggestions = mutableListOf<String>()
    listOfVacancyTitles.forEach { vacancyFunctTitle ->
        listOfSuggestions.add(vacancyFunctTitle.text)
    }
    return listOfSuggestions.toList()
}
