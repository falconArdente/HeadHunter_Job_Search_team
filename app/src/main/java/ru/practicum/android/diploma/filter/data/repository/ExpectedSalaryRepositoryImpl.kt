package ru.practicum.android.diploma.filter.data.repository

import ru.practicum.android.diploma.filter.data.storage.FilterStorage
import ru.practicum.android.diploma.filter.domain.impl.ExpectedSalaryRepository

class ExpectedSalaryRepositoryImpl(private val filterStorage: FilterStorage) : ExpectedSalaryRepository {
    override fun getExpectedSalary(): String {
        val savedFilter = filterStorage.getFilterParameters()
        return savedFilter.salaryInfo.expectedSalary ?: ""
    }

    override fun saveExpectedSalary(salaryAmount: String) {
        val savedFilter = filterStorage.getFilterParameters()
        val salaryInfoSaved = savedFilter.salaryInfo
        filterStorage.saveFilterParameters(
            savedFilter.copy(
                salaryInfo = salaryInfoSaved.copy(
                    expectedSalary = salaryAmount
                )
            )
        )
    }

    override fun clearExpectedSalary() {
        val savedFilter = filterStorage.getFilterParameters()
        val salaryInfoSaved = savedFilter.salaryInfo
        filterStorage.saveFilterParameters(
            savedFilter.copy(
                salaryInfo = salaryInfoSaved.copy(
                    expectedSalary = null
                )
            )
        )
    }
}
