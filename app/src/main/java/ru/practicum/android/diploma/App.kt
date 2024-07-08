package ru.practicum.android.diploma

import android.app.Application

class App : Application() {
    companion object {
        const val USER_AGENT = "User-Agent: FutureJob/1.0 (79950321710@yandex.ru)"
        const val LOCALE="RU"
        const val HOST="hh.ru"
    }
}
