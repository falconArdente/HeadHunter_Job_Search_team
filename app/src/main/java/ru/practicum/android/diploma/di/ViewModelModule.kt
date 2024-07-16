package ru.practicum.android.diploma.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.search.ui.SearchViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import ru.practicum.android.diploma.details.presentation.viewmodel.VacancyDetailsViewModel

val viewModelModule = module {
    viewModel<VacancyDetailsViewModel> { (vacancyId: String) ->
        VacancyDetailsViewModel(
            application = androidApplication(),
            getVacancyDetailsUseCase = get(),
            navigatorInteractor = get(),
            vacancyId = get { parametersOf(vacancyId) }
        )
    }
    viewModel<SearchViewModel> {
        SearchViewModel(getSuggestsUseCase = get())
    }
}
