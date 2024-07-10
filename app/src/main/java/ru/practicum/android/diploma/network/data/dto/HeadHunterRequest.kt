package ru.practicum.android.diploma.network.data.dto

sealed class HeadHunterRequest {
    data object Locales : HeadHunterRequest()
    data object Dictionaries : HeadHunterRequest()
    data object Industries : HeadHunterRequest()
    data object Areas : HeadHunterRequest()
    data object Counties : HeadHunterRequest()
    data class VacancySuggestions(val textForSuggestions: String) : HeadHunterRequest()
    data class VacancySearch(val textForSearch: String) : HeadHunterRequest()
    data class VacancyById(val id: String) : HeadHunterRequest()
}
