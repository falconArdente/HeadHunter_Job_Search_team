package ru.practicum.android.diploma.network.data.dto.responses

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.network.data.dto.linked.Contacts
import ru.practicum.android.diploma.network.data.dto.linked.Employer
import ru.practicum.android.diploma.network.data.dto.linked.Salary

class VacancyByIdResponse(
    @SerializedName("description") val description: String,
    @SerializedName("employer") val employer: Employer?,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("salary") val salary: Salary?,
    @SerializedName("contacts") val contacts: Contacts?,
) : Response()
