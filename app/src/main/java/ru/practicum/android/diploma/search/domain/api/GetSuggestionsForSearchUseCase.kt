package ru.practicum.android.diploma.search.domain.api

import kotlinx.coroutines.flow.Flow

fun interface GetSuggestionsForSearchUseCase {
    suspend fun execute(textOfRequest: String): Flow<List<String>>
}
