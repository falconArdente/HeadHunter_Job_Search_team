package ru.practicum.android.diploma.filter.domain.model

data class Filter(
    val countryId: String? = null,
    val countryName: String? = null,
    val areaId: String? = null,
    val areaName: String? = null,
    val industryId: String? = null,
    val industryName: String? = null,
    val salaryInfo: SalaryInfo = SalaryInfo()
)
