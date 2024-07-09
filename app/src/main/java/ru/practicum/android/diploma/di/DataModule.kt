package ru.practicum.android.diploma.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.practicum.android.diploma.db.data.db.AppDatabase
import ru.practicum.android.diploma.details.data.externalnavigator.ExternalNavigator
import ru.practicum.android.diploma.filter.data.storage.FilterStorage
import ru.practicum.android.diploma.filter.data.storage.SharedPrefsStorage

const val FUTUREJOB_SHARED_PREFS = "ru.practicum.android.diploma.MY_PREFS"

val dataModule = module {
    single {
        androidContext().getSharedPreferences(FUTUREJOB_SHARED_PREFS, Context.MODE_PRIVATE)
    }

    factory { Gson() }

    single {
        ExternalNavigator(context = androidContext())
    }

    single<FilterStorage> {
        SharedPrefsStorage(sharedPreferences = get(), gson = get())
    }

    single {
        Room.databaseBuilder(
            androidContext(), AppDatabase::class.java, "database.db"
        )
            .fallbackToDestructiveMigration()
            .build()

    }

}
