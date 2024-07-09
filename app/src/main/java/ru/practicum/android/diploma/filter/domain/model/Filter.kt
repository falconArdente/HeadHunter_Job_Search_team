package ru.practicum.android.diploma.filter.domain.model

class Filter(
    val countryId: String? = null,
    val regionId: String? = null,
    val industryId: String? = null,
    val expectedSalary: Int? = null,
    val hideNoSalaryItems: Boolean = false,
)
