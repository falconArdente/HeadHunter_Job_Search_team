package ru.practicum.android.diploma.di

import android.content.Context
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

const val FUTUREJOB_SHARED_PREFS = "ru.practicum.android.diploma.MY_PREFS"

val dataModule = module {
    single {
        androidContext().getSharedPreferences(FUTUREJOB_SHARED_PREFS, Context.MODE_PRIVATE)
    }

    factory { Gson() }

}
