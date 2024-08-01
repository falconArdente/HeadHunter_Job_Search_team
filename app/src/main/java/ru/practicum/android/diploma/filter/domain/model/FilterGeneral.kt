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
        if (this.area?.areaId.toString() != other.area?.areaId.toString()) return false
        if (this.country?.countryId.toString() != other.country?.countryId.toString()) return false
        if (this.industry?.industryId.toString() != other.industry?.industryId.toString()) return false
        if (this.hideNoSalaryItems != other.hideNoSalaryItems) return false
        if (this.expectedSalary.toString() != other.expectedSalary.toString()) return false
        return true
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}
