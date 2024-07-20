package ru.practicum.android.diploma.filter.domain.impl

interface ExpectedSalaryRepository {
    fun getExpectedSalary(): String

    fun saveExpectedSalary(salaryAmount: String)

    fun clearExpectedSalary()
}
