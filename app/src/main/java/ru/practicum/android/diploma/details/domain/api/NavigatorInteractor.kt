package ru.practicum.android.diploma.details.domain.api

interface NavigatorInteractor {
    fun shareLink(link: String)

    fun sendEmail(email: String)

    fun dialNumber(number: String)
}
