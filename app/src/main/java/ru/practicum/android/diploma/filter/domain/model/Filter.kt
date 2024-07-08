package ru.practicum.android.diploma.filter.domain.model

class Filter(
    val country: String? = null,
    val region: String? = null,
    val sector: String? = null,
    val expectedSalary: Int? = null,
    val hideNoSalaryItems: Boolean = false,
)
