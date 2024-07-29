package ru.practicum.android.diploma.filter.domain.model

data class FilterGeneral(
    val country: CountryFilter? = null,
    val area: AreaFilter? = null,
    val industry: IndustryFilter? = null,
    val expectedSalary: String? = null,
    val hideNoSalaryItems: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (other !is FilterGeneral) return false
        val source=prepareFilter(this)
        val dest=prepareFilter(other)
        if (source.area?.areaId != dest.area?.areaId) return false
        if (source.country?.countryId != dest.country?.countryId) return false
        if (source.industry?.industryId != dest.industry?.industryId) return false
        if (source.hideNoSalaryItems != dest.hideNoSalaryItems) return false
        if (source.expectedSalary != dest.expectedSalary) return false
        return true
    }

    private fun prepareFilter(filter: FilterGeneral): FilterGeneral {
        return FilterGeneral(
            area = if (filter.area?.areaId == null) AreaFilter(areaId = String()) else filter.area,
            country = if (filter.country?.countryId == null) CountryFilter(countryId = String()) else filter.country,
            industry = if (filter.industry?.industryId == null) IndustryFilter(industryId = String()) else filter.industry,
            hideNoSalaryItems = filter.hideNoSalaryItems,
            expectedSalary = if (filter.expectedSalary == null) String() else expectedSalary
        )
    }
}
