package ru.practicum.android.diploma.search.data.repository

import ru.practicum.android.diploma.network.data.dto.linked.VacancyAtSearch
import ru.practicum.android.diploma.network.data.dto.responses.VacancyListResponse
import ru.practicum.android.diploma.search.domain.model.VacancyModel
import ru.practicum.android.diploma.search.domain.model.VacancyModelResponce

class SearchVacancyConverter {
    fun mapToVacancyModelResponce(vacancy: VacancyListResponse): VacancyModelResponce {
        return VacancyModelResponce(
            mapToListVacancyModel(vacancy.vacancyAtSearchList),
            vacancy.currentPage,
            vacancy.totalFound,
            vacancy.totalPages,
            vacancy.countForPage,
        )
    }
}

fun mapToVacancyModel(vacancy: VacancyAtSearch): VacancyModel {
    return VacancyModel(
        vacancy.id,
        vacancy.name,
        vacancy.salary,
        vacancy.url,
        vacancy.brandSnippet,
        vacancy.contacts
    )
}

fun mapToListVacancyModel(vacancy: List<VacancyAtSearch>): List<VacancyModel> {
    return vacancy.map { vacancy -> mapToVacancyModel(vacancy) }
}
