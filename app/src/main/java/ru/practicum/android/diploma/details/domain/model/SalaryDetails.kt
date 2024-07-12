package ru.practicum.android.diploma.details.domain.model

import com.google.gson.annotations.SerializedName

class SalaryDetails(
    val currency: String,
    val from: Int?,
    val gross: Boolean,
    val to: Int?,
)
