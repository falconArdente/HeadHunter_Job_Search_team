package ru.practicum.android.diploma.db.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class SalaryJoinCurrency(
    @Embedded
    val salary: SalaryEntity,
    @Relation(
        parentColumn = "currencyCode",
        entityColumn = "code"
    )
    val currency: CurrencyEntity
)
