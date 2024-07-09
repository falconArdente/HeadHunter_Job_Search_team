package ru.practicum.android.diploma.network.dto.responses

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.network.dto.linkedClasses.VacancyFunctionalityTitle

class VacancySuggestionsResponse(
    @SerializedName("items")
    val vacancyPositionsList: List<VacancyFunctionalityTitle>
) : Response()
