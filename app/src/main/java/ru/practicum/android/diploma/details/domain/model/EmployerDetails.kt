package ru.practicum.android.diploma.details.domain.model


class EmployerDetails(
    val id: String?,
    val name: String,
    val url: String?,
    val vacanciesUrl: String?,
    val isTrusted: Boolean,
    val logoUrls: LogoUrlsDetails?,
)
