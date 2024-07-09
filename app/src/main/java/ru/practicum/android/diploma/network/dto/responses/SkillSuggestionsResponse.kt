package ru.practicum.android.diploma.network.dto.responses

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.network.dto.Skill

class SkillSuggestionsResponse(
    @SerializedName("items")
    val skillsList: List<Skill>
) : Response()
