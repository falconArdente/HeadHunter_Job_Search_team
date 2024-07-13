package ru.practicum.android.diploma.search.data.mapper

import ru.practicum.android.diploma.network.data.dto.linked.BrandSnippet
import ru.practicum.android.diploma.network.data.dto.linked.Salary
import ru.practicum.android.diploma.network.data.dto.linked.VacancyAtSearch
import ru.practicum.android.diploma.network.data.dto.responses.VacancyListResponse
import ru.practicum.android.diploma.search.domain.model.BrandSnippetModel
import ru.practicum.android.diploma.search.domain.model.SalaryModel
import ru.practicum.android.diploma.search.domain.model.Vacancy

class SearchVacancyConverter {
    fun mapToVacancyModel(vacancy: VacancyAtSearch): Vacancy{
        return Vacancy(
            vacancy.id,
            vacancy.name,
            mapToSalaryModel(vacancy.salary),
            vacancy.url,
            mapToBrandSnippetModel(vacancy.brandSnippet!!),
            vacancy.contacts
        )
    }

    fun mapToListVacancyModel(vacancy: List<VacancyAtSearch>): List<Vacancy> {
        return vacancy.map { vacancy -> mapToVacancyModel(vacancy) }
    }

    fun mapToSalaryModel(salary: Salary): SalaryModel {
        return SalaryModel(
            salary.currency,
            salary.from,
            salary.gross,
            salary.to
        )
    }

    fun mapToBrandSnippetModel(brSnippet: BrandSnippet): BrandSnippetModel {
        return BrandSnippetModel(
            brSnippet.logo,
            brSnippet.logoXs,
            brSnippet.picture,
            brSnippet.pictureXs
        )
    }
}
