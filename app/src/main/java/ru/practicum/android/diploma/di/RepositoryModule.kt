package ru.practicum.android.diploma.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.practicum.android.diploma.details.data.repository.NavigatorRepositoryImpl
import ru.practicum.android.diploma.details.domain.api.NavigatorRepository
import ru.practicum.android.diploma.filter.data.repository.FilterStorageRepositoryImpl
import ru.practicum.android.diploma.filter.domain.api.FilterStorageRepository
import ru.practicum.android.diploma.network.data.HeadHunterRepository
import ru.practicum.android.diploma.search.data.repository.SearchRepository

val repositoryModule = module {
    single<NavigatorRepository> {
        NavigatorRepositoryImpl(externalNavigator = get())
    }

    single<FilterStorageRepository> {
        FilterStorageRepositoryImpl(filterStorage = get())
    }

    factory<SearchRepository> {
        HeadHunterRepository(client = get(), context = androidContext())
    }
}
