package ru.practicum.android.diploma.network.dto

sealed class HeadHunterRequest {
    data object LocalesList : HeadHunterRequest()
}
