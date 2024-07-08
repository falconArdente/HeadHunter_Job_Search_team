package ru.practicum.android.diploma.filter.domain.model

class Filter(
    val country: String,
    val region: String,
    val area: String,
    val expectedSalary: Int,
    val hideNoSalaryPositions: Boolean,
)
