package ru.practicum.android.diploma.search.domain.impl

import ru.practicum.android.diploma.filter.domain.impl.FilterStorageRepository
import ru.practicum.android.diploma.search.data.mapper.SearchVacancyConverter
import ru.practicum.android.diploma.search.domain.api.GetFilterUseCase
import ru.practicum.android.diploma.search.domain.model.SearchParameters

class GetFilterUseCaseImpl(private val filterRepository: FilterStorageRepository) : GetFilterUseCase {
    override fun execute(): SearchParameters? {
        return SearchVacancyConverter().toSearchParameters(filterRepository.getAllFilterParameters())
    }
}
