package ru.practicum.android.diploma.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.search.ui.SearchJobViewModel

val viewModelModule = module {
    viewModel<SearchJobViewModel> {
        SearchJobViewModel(getSuggestsUseCase = get())
    }
}
