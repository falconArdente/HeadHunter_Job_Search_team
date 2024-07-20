package ru.practicum.android.diploma.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.practicum.android.diploma.db.data.db.VacancyDbConvertor
import ru.practicum.android.diploma.db.data.impl.VacancyRepositoryImpl
import ru.practicum.android.diploma.db.domain.api.VacancyRepository
import ru.practicum.android.diploma.details.data.repository.NavigatorRepositoryImpl
import ru.practicum.android.diploma.details.domain.api.NavigatorRepository
import ru.practicum.android.diploma.details.domain.impl.VacancyDetailsRepository
import ru.practicum.android.diploma.favorites.data.repository.FavoritesRepositoryRoomBased
import ru.practicum.android.diploma.favorites.domain.impl.LocalRepository
import ru.practicum.android.diploma.filter.data.repository.AllFilterParametersRepositoryImpl
import ru.practicum.android.diploma.filter.data.repository.AreaFilterRepositoryImpl
import ru.practicum.android.diploma.filter.data.repository.CountryFilterRepositoryImpl
import ru.practicum.android.diploma.filter.data.repository.ExpectedSalaryRepositoryImpl
import ru.practicum.android.diploma.filter.data.repository.HideNoSalaryItemsRepositoryImpl
import ru.practicum.android.diploma.filter.data.repository.SphereOfIndustryRepositoryImpl
import ru.practicum.android.diploma.filter.domain.impl.AllFilterParameterRepository
import ru.practicum.android.diploma.filter.domain.impl.AreaFilterRepository
import ru.practicum.android.diploma.filter.domain.impl.CountryFilterRepository
import ru.practicum.android.diploma.filter.domain.impl.ExpectedSalaryRepository
import ru.practicum.android.diploma.filter.domain.impl.HideNoSalaryItemsRepository
import ru.practicum.android.diploma.filter.domain.impl.SphereOfIndustryRepository
import ru.practicum.android.diploma.network.data.HeadHunterRepository
import ru.practicum.android.diploma.search.data.repository.SearchRepository

val repositoryModule = module {
    single<NavigatorRepository> {
        NavigatorRepositoryImpl(externalNavigator = get())
    }

    single<AllFilterParameterRepository> {
        AllFilterParametersRepositoryImpl(filterStorage = get())
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

    single<AreaFilterRepository> {
        AreaFilterRepositoryImpl(filterStorage = get())
    }

    single<CountryFilterRepository> {
        CountryFilterRepositoryImpl(filterStorage = get())
    }

    single<ExpectedSalaryRepository> {
        ExpectedSalaryRepositoryImpl(filterStorage = get())
    }

    single<HideNoSalaryItemsRepository> {
        HideNoSalaryItemsRepositoryImpl(filterStorage = get())
    }

    single<SphereOfIndustryRepository> {
        SphereOfIndustryRepositoryImpl(filterStorage = get())
    }

}
