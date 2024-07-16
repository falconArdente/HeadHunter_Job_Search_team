package ru.practicum.android.diploma.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.practicum.android.diploma.db.data.db.VacancyDbConvertor
import ru.practicum.android.diploma.db.domain.api.VacancyRepository
import ru.practicum.android.diploma.db.data.impl.VacancyRepositoryImpl
import ru.practicum.android.diploma.details.data.repository.NavigatorRepositoryImpl
import ru.practicum.android.diploma.details.domain.api.NavigatorRepository
import ru.practicum.android.diploma.details.domain.impl.VacancyDetailsRepository
import ru.practicum.android.diploma.favorites.data.repository.FavoritesRepositoryRoomBased
import ru.practicum.android.diploma.favorites.domain.impl.LocalRepository
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

    factory { VacancyDbConvertor() }

    single<VacancyRepository> {
        VacancyRepositoryImpl(appDatabase = get(), vacancyDbConvertor = get())
    }

    factory<VacancyDetailsRepository> {
        HeadHunterRepository(client = get(), context = androidContext())
    }
    factory<LocalRepository> {
        FavoritesRepositoryRoomBased()
    }
}
