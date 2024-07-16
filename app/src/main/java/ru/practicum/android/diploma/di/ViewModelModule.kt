package ru.practicum.android.diploma.di

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import ru.practicum.android.diploma.details.presentation.viewmodel.VacancyDetailsViewModel
import ru.practicum.android.diploma.favorites.presentation.viewmodel.FavoritesViewModel

val viewModelModule = module {
    viewModel<VacancyDetailsViewModel> { (vacancyId: String) ->
        VacancyDetailsViewModel(
            application = androidApplication(),
            getVacancyDetailsUseCase = get(),
            navigatorInteractor = get(),
            vacancyId = get { parametersOf(vacancyId) }
        )
    }
    viewModel<FavoritesViewModel> {
        FavoritesViewModel(getFavoritesListUseCase = get())
    }
}
