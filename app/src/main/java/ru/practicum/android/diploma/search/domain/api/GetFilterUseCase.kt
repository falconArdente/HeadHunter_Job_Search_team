package ru.practicum.android.diploma.search.domain.api

import ru.practicum.android.diploma.search.domain.model.SearchParameters

fun interface GetFilterUseCase {
    fun execute(): SearchParameters?
}
