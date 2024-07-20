package ru.practicum.android.diploma.filter.domain.model

data class Filter(
    val countryId: String? = null,
    val areaId: String? = null,
    val industryId: String? = null,
    val expectedSalary: String? = null,
    val hideNoSalaryItems: Boolean = false,
)
