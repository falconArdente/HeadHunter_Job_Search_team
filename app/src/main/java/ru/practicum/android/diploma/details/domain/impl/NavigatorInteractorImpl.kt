package ru.practicum.android.diploma.details.domain.impl

import ru.practicum.android.diploma.details.domain.api.NavigatorInteractor
import ru.practicum.android.diploma.details.domain.api.NavigatorRepository

class NavigatorInteractorImpl(private val navigatorRepository: NavigatorRepository) : NavigatorInteractor {
    override fun shareLink(link: String) {
        navigatorRepository.shareLink(link)
    }

    override fun sendEmail(email: String) {
        navigatorRepository.sendEmail(email)
    }

    override fun dialNumber(number: String) {
        navigatorRepository.dialNumber(number)
    }

}
