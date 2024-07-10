package ru.practicum.android.diploma.network.data.dto.responses

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.network.data.dto.linked.Currency

class DictionariesResponse(
    @SerializedName("currency") val currency: List<ru.practicum.android.diploma.network.data.dto.linked.Currency>
) : ru.practicum.android.diploma.network.data.dto.responses.Response()
