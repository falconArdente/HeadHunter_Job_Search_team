package ru.practicum.android.diploma.search.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.network.data.dto.linked.VacancyFunctTitle
import ru.practicum.android.diploma.network.data.mapper.vacancyFuncTitleListToListForSuggestions
import ru.practicum.android.diploma.search.domain.api.GetSuggestionsForSearchUseCase
import ru.practicum.android.diploma.utils.Resource

class GetSuggestionsForSearchUseCaseImpl(private val repository: SearchRepository) : GetSuggestionsForSearchUseCase {
    override suspend fun execute(textOfRequest: String): Flow<List<String>> {
        return flow {
            repository
                .getVacancySuggestions(textOfRequest)
                .collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            emit(vacancyFuncTitleListToListForSuggestions(resource.data as List<VacancyFunctTitle>))
                        }

                        is Resource.Error, is Resource.InternetConnectionError, is Resource.NotFoundError -> {
                            emit(emptyList())
                        }
                    }
                }
        }
    }
}
