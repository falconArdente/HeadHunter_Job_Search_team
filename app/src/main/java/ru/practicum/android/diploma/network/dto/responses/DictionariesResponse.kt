package ru.practicum.android.diploma.network.dto.responses

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.network.dto.linked.Currency

class DictionariesResponse(
    @SerializedName("currency") val currency: List<Currency>
) : Response()
