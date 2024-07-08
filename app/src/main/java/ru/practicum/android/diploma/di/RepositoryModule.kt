package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.network.HeadHunterRepository
import ru.practicum.android.diploma.search.data.repository.SearchRepository

val repositoryModule = module {

    factory<SearchRepository> {
        HeadHunterRepository(client = get())
    }
}
