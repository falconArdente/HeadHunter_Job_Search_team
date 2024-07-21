package ru.practicum.android.diploma.filter.domain.impl

import ru.practicum.android.diploma.filter.domain.model.IndustryDetailsFilterItem
import ru.practicum.android.diploma.filter.domain.model.IndustryFilter

interface IndustryRepository {
    fun getIndustry(): IndustryFilter

    fun saveIndustry(industry: IndustryDetailsFilterItem)

    fun clearIndustry()
}
