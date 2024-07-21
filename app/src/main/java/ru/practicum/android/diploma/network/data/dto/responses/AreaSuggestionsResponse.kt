package ru.practicum.android.diploma.network.data.dto.responses

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.network.data.dto.linked.AreaSuggestionDTO

class AreaSuggestionsResponse(
    @SerializedName("items") val suggestions: List<AreaSuggestionDTO>
) : Response()
