package ru.practicum.android.diploma.network.data.dto

sealed class HeadHunterRequest {
    data object Locales : ru.practicum.android.diploma.network.data.dto.HeadHunterRequest()
    data object Dictionaries : ru.practicum.android.diploma.network.data.dto.HeadHunterRequest()
    data object Industries : ru.practicum.android.diploma.network.data.dto.HeadHunterRequest()
    data object Areas : ru.practicum.android.diploma.network.data.dto.HeadHunterRequest()
    data object Counties : ru.practicum.android.diploma.network.data.dto.HeadHunterRequest()
    data class VacancySuggestions(val textForSuggestions: String) : ru.practicum.android.diploma.network.data.dto.HeadHunterRequest()
    data class VacancySearch(val textForSearch: String) : ru.practicum.android.diploma.network.data.dto.HeadHunterRequest()
    data class VacancyById(val id: String) : ru.practicum.android.diploma.network.data.dto.HeadHunterRequest()
}
