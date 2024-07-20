package ru.practicum.android.diploma.filter.data.repository

import ru.practicum.android.diploma.filter.data.storage.FilterStorage
import ru.practicum.android.diploma.filter.domain.impl.ExpectedSalaryRepository

class ExpectedSalaryRepositoryImpl(private val filterStorage: FilterStorage) : ExpectedSalaryRepository {
    override fun getExpectedSalary(): String {
        val savedFilter = filterStorage.getFilterParameters()
        return savedFilter.expectedSalary ?: ""
    }

    override fun saveExpectedSalary(salaryAmount: String) {
        val savedFilter = filterStorage.getFilterParameters()
        filterStorage.saveFilterParameters(savedFilter.copy(expectedSalary = salaryAmount))
    }

    override fun clearExpectedSalary() {
        val savedFilter = filterStorage.getFilterParameters()
        filterStorage.saveFilterParameters(savedFilter.copy(expectedSalary = null))
    }
}
