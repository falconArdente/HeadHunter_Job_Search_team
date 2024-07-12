package ru.practicum.android.diploma.network.data.dto.responses

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.network.data.dto.linked.Area
import ru.practicum.android.diploma.network.data.dto.linked.Contacts
import ru.practicum.android.diploma.network.data.dto.linked.Employer
import ru.practicum.android.diploma.network.data.dto.linked.Employment
import ru.practicum.android.diploma.network.data.dto.linked.Experience
import ru.practicum.android.diploma.network.data.dto.linked.Salary
import ru.practicum.android.diploma.network.data.dto.linked.Skill

class VacancyByIdResponse(
    @SerializedName("description") val description: String,
    @SerializedName("employer") val employer: Employer?,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("salary") val salary: Salary?,
    @SerializedName("contacts") val contacts: Contacts?,
    @SerializedName("area") val area: Area, // необходимо отдельная сущность?
    @SerializedName("experience") val experience: Experience?,
    @SerializedName("employment") val employment: Employment?,
    @SerializedName("key_skills") val keySkills: List<Skill>,
    @SerializedName("alternate_url") val vacancyUrl: String,
) : Response()
