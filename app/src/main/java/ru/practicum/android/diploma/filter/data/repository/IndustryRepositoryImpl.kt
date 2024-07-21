package ru.practicum.android.diploma.filter.data.repository

import ru.practicum.android.diploma.filter.data.storage.FilterStorage
import ru.practicum.android.diploma.filter.domain.impl.IndustryRepository
import ru.practicum.android.diploma.filter.domain.model.IndustryDetailsFilterItem
import ru.practicum.android.diploma.filter.domain.model.IndustryFilter

class IndustryRepositoryImpl(private val filterStorage: FilterStorage) : IndustryRepository {
    override fun getIndustry(): IndustryFilter {
        val savedFilter = filterStorage.getFilterParameters()
        // ВАЖНО для дальнейшей обработки! если регион ранее не сохранялся в фильтре,т.е. area в Filter null,
        // то вернет пустой объект IndustryFilter(), где поля id == null и name == null
        return savedFilter.industry ?: IndustryFilter()
    }

    override fun saveIndustry(industry: IndustryDetailsFilterItem) {
        val savedFilter = filterStorage.getFilterParameters()
        filterStorage.saveFilterParameters(
            savedFilter.copy(
                industry = IndustryFilter(industryId = industry.industryId, industryName = industry.industryName)
            )
        )
    }

    override fun clearIndustry() {
        val savedFilter = filterStorage.getFilterParameters()
        filterStorage.saveFilterParameters(savedFilter.copy(industry = null))
    }
}
