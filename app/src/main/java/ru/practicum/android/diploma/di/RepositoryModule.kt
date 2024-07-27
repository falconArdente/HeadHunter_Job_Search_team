package ru.practicum.android.diploma.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.practicum.android.diploma.db.data.db.VacancyDbConvertor
import ru.practicum.android.diploma.db.data.repository.VacancyRepositoryImpl
import ru.practicum.android.diploma.search.domain.impl.VacancyRepository
import ru.practicum.android.diploma.details.data.repository.NavigatorRepositoryImpl
import ru.practicum.android.diploma.details.domain.impl.NavigatorRepository
import ru.practicum.android.diploma.details.domain.impl.VacancyDetailsRepository
import ru.practicum.android.diploma.favorites.data.repository.FavoritesRepositoryRoomBased
import ru.practicum.android.diploma.favorites.domain.impl.LocalRepository
import ru.practicum.android.diploma.filter.data.repository.FilterDictionariesRepositoryHHNetworkClientBased
import ru.practicum.android.diploma.filter.domain.impl.FilterDictionariesRepository
import ru.practicum.android.diploma.filter.data.repository.FilterStorageRepositoryImpl
import ru.practicum.android.diploma.filter.domain.impl.FilterStorageRepository
import ru.practicum.android.diploma.network.data.repository.HeadHunterRepository
import ru.practicum.android.diploma.search.domain.impl.SearchRepository

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
        FavoritesRepositoryRoomBased(appDatabase = get(), vacancyDbConvertor = get())
    }

    factory<FilterDictionariesRepository> {
        FilterDictionariesRepositoryHHNetworkClientBased(client = get(), context = androidContext())
    }
}
