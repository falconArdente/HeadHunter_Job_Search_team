package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.network.data.dto.linked.VacancyAtSearch
import ru.practicum.android.diploma.network.data.dto.responses.VacancyListResponse
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.search.domain.model.VacancyModelResponse

class SearchVacancyConverter {
    fun mapToVacancyModelResponce(vacancy: VacancyListResponse): VacancyModelResponse {
        return VacancyModelResponse(
            mapToListVacancyModel(vacancy.vacancyAtSearchList),
            vacancy.currentPage,
            vacancy.totalFound,
            vacancy.totalPages,
            vacancy.countForPage,
        )
    }
}

fun mapToVacancyModel(vacancy: VacancyAtSearch): Vacancy {
    return Vacancy(
        vacancy.id,
        vacancy.name,
        vacancy.salary,
        vacancy.url,
        vacancy.brandSnippet,
        vacancy.contacts
    )
}

fun mapToListVacancyModel(vacancy: List<VacancyAtSearch>): List<Vacancy> {
    return vacancy.map { vacancy -> mapToVacancyModel(vacancy) }
}
