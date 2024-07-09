package ru.practicum.android.diploma.network.dto.linked

import com.google.gson.annotations.SerializedName

class BrandSnippet(
    @SerializedName("logo") var logo: String?,
    @SerializedName("logo_xs") var logoXs: String?,
    @SerializedName("picture") var picture: String?,
    @SerializedName("picture_xs") var pictureXs: String?,
)
