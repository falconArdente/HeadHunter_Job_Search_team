package ru.practicum.android.diploma.network.dto.linked

import com.google.gson.annotations.SerializedName

class Contacts(
    @SerializedName("email") val email: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("phones") val phones: List<Phone>?,
)
