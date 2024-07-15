package ru.practicum.android.diploma.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.db.data.db.AppDatabase
import ru.practicum.android.diploma.details.data.externalnavigator.ExternalNavigator
import ru.practicum.android.diploma.filter.data.storage.FilterStorage
import ru.practicum.android.diploma.filter.data.storage.SharedPrefsStorage
import ru.practicum.android.diploma.network.data.RetrofitBasedClient
import ru.practicum.android.diploma.network.data.api.HeadHunterNetworkClient
import ru.practicum.android.diploma.search.data.mapper.SearchVacancyConverter
import ru.practicum.android.diploma.utils.NetworkStatus

const val FUTUREJOB_SHARED_PREFS = "ru.practicum.android.diploma.MY_PREFS"
const val BASE_URL = "https://api.hh.ru"
val dataModule = module {
    single {
        androidContext().getSharedPreferences(FUTUREJOB_SHARED_PREFS, Context.MODE_PRIVATE)
    }
    factory { Gson() }
    factory<HeadHunterNetworkClient> {
        RetrofitBasedClient(retrofit = get(), networkStatus = get())
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>()) // interception client
            .build()
    }
    single<OkHttpClient> {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    single {
        ExternalNavigator(context = androidContext())
    }

    single<FilterStorage> {
        SharedPrefsStorage(sharedPreferences = get(), gson = get())
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "database.db"
        )
            .fallbackToDestructiveMigration()
            .build()

    }

    factory<NetworkStatus> {
        NetworkStatus(androidContext())
    }
    factory { SearchVacancyConverter() }
}
