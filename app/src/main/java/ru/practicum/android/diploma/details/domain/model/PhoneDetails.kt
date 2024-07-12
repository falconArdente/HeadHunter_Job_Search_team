package ru.practicum.android.diploma.details.domain.model

import com.google.gson.annotations.SerializedName

class PhoneDetails(
    val city: String,
    val comment: String?,
    val country: String,
    val formatted: String,
    val number: String,
)
