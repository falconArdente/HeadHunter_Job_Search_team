package ru.practicum.android.diploma.di

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import ru.practicum.android.diploma.details.presentation.viewmodel.VacancyDetailsViewModel
import ru.practicum.android.diploma.favorites.presentation.viewmodel.FavoritesViewModel
import ru.practicum.android.diploma.filter.presentation.viewmodel.CountryFilterViewModel
import ru.practicum.android.diploma.filter.presentation.viewmodel.FilterIndustryViewModel
import ru.practicum.android.diploma.filter.presentation.viewmodel.FilterSettingsViewModel
import ru.practicum.android.diploma.filter.presentation.viewmodel.PlaceToWorkFilterViewModel
import ru.practicum.android.diploma.filter.presentation.viewmodel.RegionFilterViewModel
import ru.practicum.android.diploma.search.presentation.viewmodel.SearchViewModel

val viewModelModule = module {
    viewModel<VacancyDetailsViewModel> { (vacancyId: String) ->
        VacancyDetailsViewModel(
            application = androidApplication(),
            getVacancyDetailsUseCase = get(),
            navigatorInteractor = get(),
            detailsDbInteractor = get(),
            vacancyId = get { parametersOf(vacancyId) }
        )
    }

    viewModel<SearchViewModel> {
        SearchViewModel(
            interactor = get(),
            getSuggestsUseCase = get(),
            getFilterUseCase = get(),
            application = androidApplication()
        )
    }
    viewModel<FavoritesViewModel> {
        FavoritesViewModel(getFavoritesListUseCase = get())
    }

    viewModel<CountryFilterViewModel> {
        CountryFilterViewModel(countryFilterInteractor = get())
    }

    viewModel<RegionFilterViewModel> {
        RegionFilterViewModel(regionFilterInteractor = get(), countryFilterInteractor = get())
    }

    viewModel<FilterSettingsViewModel> {
        FilterSettingsViewModel(filterStorage = get())
    }

    viewModel<FilterIndustryViewModel> {
        FilterIndustryViewModel(
            filterStorage = get(),
            filterDictionaries = get(),
            networkStatus = get()
        )
    }

    viewModel<PlaceToWorkFilterViewModel> {
        PlaceToWorkFilterViewModel(placeToWorkFilterInteractor = get())
    }
}
