package ru.practicum.android.diploma.network.dto.responses

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.network.dto.linked_classes.Contacts
import ru.practicum.android.diploma.network.dto.linked_classes.Employer
import ru.practicum.android.diploma.network.dto.linked_classes.Salary

class VacancyByIdResponse(
    @SerializedName("code") val code: String,
    @SerializedName("description") val description: String,
    @SerializedName("employer") val employer: Employer?,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("salary") val salary: Salary?,
    @SerializedName("contacts") val contacts: Contacts?,
) : Response()
