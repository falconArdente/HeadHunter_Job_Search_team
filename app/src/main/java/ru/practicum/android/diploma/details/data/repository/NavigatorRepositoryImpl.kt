package ru.practicum.android.diploma.details.data.repository

import ru.practicum.android.diploma.details.data.externalnavigator.ExternalNavigator
import ru.practicum.android.diploma.details.domain.api.NavigatorRepository

class NavigatorRepositoryImpl(private val externalNavigator: ExternalNavigator) : NavigatorRepository {
    override fun shareLink(link: String) {
        externalNavigator.shareLink(link)
    }

    override fun sendEmail(email: String) {
        externalNavigator.sendEmail(email)
    }

    override fun dialNumber(number: String) {
        externalNavigator.dialNumber(number)
    }

}
