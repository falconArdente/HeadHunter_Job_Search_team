package ru.practicum.android.diploma.di

import android.content.Context
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.network.RetrofitBasedClient
import ru.practicum.android.diploma.network.api.HeadHunterNetworkClient

const val FUTUREJOB_SHARED_PREFS = "ru.practicum.android.diploma.MY_PREFS"
const val BASE_URL = "https://api.hh.ru"
val dataModule = module {
    single {
        androidContext().getSharedPreferences(FUTUREJOB_SHARED_PREFS, Context.MODE_PRIVATE)
    }

    factory { Gson() }

    factory<HeadHunterNetworkClient> {
        RetrofitBasedClient(retrofit = get())
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
}
