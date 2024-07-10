package ru.practicum.android.diploma.network.data.dto.linked

import com.google.gson.annotations.SerializedName

class Locale(
    @SerializedName("current")
    var current: Boolean,
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String,
)
