package ru.practicum.android.diploma.filter.domain.model

data class FilterGeneral(
    val country: CountryFilter? = null,
    val area: AreaFilter? = null,
    val industry: IndustryFilter? = null,
    val expectedSalary: String? = null,
    val hideNoSalaryItems: Boolean = false
)
