package ru.practicum.android.diploma.details.domain.model

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.network.data.dto.linked.LogoUrls

class EmployerDetails(
    val id: String?,
    val name: String,
    val url: String?,
    val vacanciesUrl: String?,
    val isTrusted: Boolean,
    val logoUrls: LogoUrlsDetails?,
)
