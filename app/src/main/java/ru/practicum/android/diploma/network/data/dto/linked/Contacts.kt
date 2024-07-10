package ru.practicum.android.diploma.network.data.dto.linked

import com.google.gson.annotations.SerializedName

class Contacts(
    @SerializedName("email") val email: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("phones") val phones: List<ru.practicum.android.diploma.network.data.dto.linked.Phone>?,
)
