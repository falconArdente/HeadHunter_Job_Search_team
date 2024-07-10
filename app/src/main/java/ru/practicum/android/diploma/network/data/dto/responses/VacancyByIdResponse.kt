package ru.practicum.android.diploma.network.data.dto.responses

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.network.data.dto.linked.Contacts
import ru.practicum.android.diploma.network.data.dto.linked.Employer
import ru.practicum.android.diploma.network.data.dto.linked.Salary

class VacancyByIdResponse(
    @SerializedName("description") val description: String,
    @SerializedName("employer") val employer: ru.practicum.android.diploma.network.data.dto.linked.Employer?,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("salary") val salary: ru.practicum.android.diploma.network.data.dto.linked.Salary?,
    @SerializedName("contacts") val contacts: ru.practicum.android.diploma.network.data.dto.linked.Contacts?,
) : ru.practicum.android.diploma.network.data.dto.responses.Response()
