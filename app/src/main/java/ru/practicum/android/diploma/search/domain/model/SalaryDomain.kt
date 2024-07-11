package ru.practicum.android.diploma.search.domain.model

import com.google.gson.annotations.SerializedName

class SalaryDomain(
    val currency: String,
    val from: Int?,
    val gross: Boolean,
    val to: Int?,
)
