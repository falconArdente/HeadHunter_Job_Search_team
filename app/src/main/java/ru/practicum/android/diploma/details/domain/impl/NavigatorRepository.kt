package ru.practicum.android.diploma.details.domain.impl

interface NavigatorRepository {
    fun shareLink(link: String)

    fun sendEmail(email: String)

    fun dialNumber(number: String)
}
