package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.details.domain.api.DetailsDbInteractor
import ru.practicum.android.diploma.details.domain.api.GetVacancyDetailsUseCase
import ru.practicum.android.diploma.details.domain.api.NavigatorInteractor
import ru.practicum.android.diploma.details.domain.impl.DetailsDbInteractorImpl
import ru.practicum.android.diploma.details.domain.impl.GetVacancyDetailsUseCaseImpl
import ru.practicum.android.diploma.details.domain.impl.NavigatorInteractorImpl
import ru.practicum.android.diploma.favorites.domain.api.FavoriteDbInteractor
import ru.practicum.android.diploma.favorites.domain.api.GetFavoritesListUseCase
import ru.practicum.android.diploma.favorites.domain.impl.FavoriteDbInteractorImpl
import ru.practicum.android.diploma.favorites.domain.impl.GetFavoritesListImpl
import ru.practicum.android.diploma.filter.domain.api.CountryFilterInteractor
import ru.practicum.android.diploma.filter.domain.api.FilterDictionariesInteractor
import ru.practicum.android.diploma.filter.domain.api.FilterStorageInteractor
import ru.practicum.android.diploma.filter.domain.api.PlaceToWorkFilterInteractor
import ru.practicum.android.diploma.filter.domain.api.RegionFilterInteractor
import ru.practicum.android.diploma.filter.domain.impl.CountryFilterInteractorImpl
import ru.practicum.android.diploma.filter.domain.impl.FilterDictionariesInteractorImpl
import ru.practicum.android.diploma.filter.domain.impl.FilterStorageInteractorImpl
import ru.practicum.android.diploma.filter.domain.impl.PlaceToWorkFilterInteractorImpl
import ru.practicum.android.diploma.filter.domain.impl.RegionFilterInteractorImpl
import ru.practicum.android.diploma.search.data.repository.GetFilterUseCaseImpl
import ru.practicum.android.diploma.search.data.repository.GetSuggestionsForSearchUseCaseImpl
import ru.practicum.android.diploma.search.data.repository.SearchInteractorImpl
import ru.practicum.android.diploma.search.domain.api.GetFilterUseCase
import ru.practicum.android.diploma.search.domain.api.GetSuggestionsForSearchUseCase
import ru.practicum.android.diploma.search.domain.api.SearchInteractor

val interactorModule = module {
    factory<NavigatorInteractor> {
        NavigatorInteractorImpl(navigatorRepository = get())
    }

    factory<GetSuggestionsForSearchUseCase> {
        GetSuggestionsForSearchUseCaseImpl(repository = get())
    }

    factory<SearchInteractor> {
        SearchInteractorImpl(repository = get(), converter = get())
    }

    factory<GetVacancyDetailsUseCase> {
        GetVacancyDetailsUseCaseImpl(vacancyDetailsRepository = get())
    }

    factory<DetailsDbInteractor> {
        DetailsDbInteractorImpl(vacancyRepository = get())
    }

    factory<FavoriteDbInteractor> {
        FavoriteDbInteractorImpl(vacancyRepository = get())
    }

    factory<GetFavoritesListUseCase> {
        GetFavoritesListImpl(repository = get())
    }

    factory<FilterDictionariesInteractor> {
        FilterDictionariesInteractorImpl(repository = get())
    }

    factory<FilterStorageInteractor> {
        FilterStorageInteractorImpl(repository = get())
    }

    factory<CountryFilterInteractor> {
        CountryFilterInteractorImpl(
            filterStorageRepository = get(),
            filterDictionariesRepository = get(),
            placeToWorkFilterInteractor = get()
        )
    }

    factory<RegionFilterInteractor> {
        RegionFilterInteractorImpl(filterStorageRepository = get(), filterDictionariesRepository = get())
    }

    factory<PlaceToWorkFilterInteractor> {
        PlaceToWorkFilterInteractorImpl(filterStorageRepository = get(), filterDictionariesRepository = get())
    }
    factory<GetFilterUseCase> {
        GetFilterUseCaseImpl(filterRepository = get())
    }
}
