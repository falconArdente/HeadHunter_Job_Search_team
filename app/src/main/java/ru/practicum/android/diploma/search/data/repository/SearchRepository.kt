package ru.practicum.android.diploma.search.data.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.network.api.Locale
import ru.practicum.android.diploma.utils.Resource

interface SearchRepository {
    suspend fun getLocales(): Flow<Resource<List<Locale>>>
}
