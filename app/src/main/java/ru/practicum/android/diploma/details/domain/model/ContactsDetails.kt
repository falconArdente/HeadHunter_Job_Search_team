package ru.practicum.android.diploma.details.domain.model

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.network.data.dto.linked.Phone

class ContactsDetails(
    val email: String?,
    val name: String?,
    val phones: List<PhoneDetails>?,
)
