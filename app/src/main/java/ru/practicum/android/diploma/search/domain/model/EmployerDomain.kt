package ru.practicum.android.diploma.search.domain.model

import com.google.gson.annotations.SerializedName

class EmployerDomain(
    val id: String?,
    val name: String,
    val url: String?,
    val vacanciesUrl: String?,
    val isTrusted: Boolean,
    val logoUrls: LogoUrlsDomain?,
)
