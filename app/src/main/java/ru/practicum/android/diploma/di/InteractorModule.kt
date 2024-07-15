package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.details.domain.api.GetVacancyDetailsUseCase
import ru.practicum.android.diploma.details.domain.api.NavigatorInteractor
import ru.practicum.android.diploma.details.domain.impl.GetVacancyDetailsUseCaseImpl
import ru.practicum.android.diploma.details.domain.impl.NavigatorInteractorImpl
import ru.practicum.android.diploma.filter.domain.api.FilterInteractor
import ru.practicum.android.diploma.filter.domain.impl.FilterInteractorImpl
import ru.practicum.android.diploma.search.data.repository.SearchInteractorImpl
import ru.practicum.android.diploma.search.domain.api.SearchInteractor

val interactorModule = module {
    factory<NavigatorInteractor> {
        NavigatorInteractorImpl(navigatorRepository = get())
    }

    factory<FilterInteractor> {
        FilterInteractorImpl(filterStorageRepository = get())
    }
    factory<SearchInteractor> {
        SearchInteractorImpl(repository = get(), converter = get())
    }

    factory<GetVacancyDetailsUseCase> {
        GetVacancyDetailsUseCaseImpl(vacancyDetailsRepository = get())
    }
}
