package ru.practicum.android.diploma.search.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.search.domain.api.GetSuggestionsForSearchUseCase
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.search.presentation.state.SearchFragmentState

private const val SEARCH_DEBOUNCE_DELAY = 2000L
private const val CLICK_DEBOUNCE_DELAY = 1000L

class SearchViewModel(
    private val interactor: SearchInteractor,
    private val getSuggestsUseCase: GetSuggestionsForSearchUseCase
) : ViewModel() {
    private val searchLiveData =
        MutableLiveData<SearchFragmentState>(SearchFragmentState.NoTextInInputEditText)
    private var latestSearchText: String? = null
    private var searchJob: Job? = null
    private var isClickAllowed = true
    private var suggestionsList = MutableLiveData<List<String>>(emptyList())
    val suggestionsLivaData: LiveData<List<String>> = suggestionsList

    private var currentPage = -1
    private var maxPages = 0
    private val vacanciesList = mutableListOf<Vacancy>()

    init {
        updateState(SearchFragmentState.NoTextInInputEditText)

    }

    fun getSuggestionsForSearch(textForSuggests: String) {
        viewModelScope.launch {
            getSuggestsUseCase.execute(textForSuggests)
                .collect {
                    suggestionsList.postValue(it)
                }
        }
    }

    fun fragmentStateLiveData(): LiveData<SearchFragmentState> = searchLiveData
    fun updateState(state: SearchFragmentState) {
        searchLiveData.postValue(state)
    }

    private val searchJobDetails: Job? = null
    private fun searchResult(text: String) {
        searchJobDetails?.cancel()
        updateState(SearchFragmentState.Loading)
        searchJobDetails != viewModelScope.launch {
            interactor
                .searchVacancy(text)
                .collect { vacancy ->
                    if (vacancy.result!!.isNotEmpty()) {
                        updateState(SearchFragmentState.SearchVacancy(vacancy.result, vacancy.foundVacancy))
                    } else if (vacancy.errorMessage!!.isNotEmpty()) {
                        updateState(SearchFragmentState.ServerError)
                    } else if (vacancy.errorMessage.isNullOrEmpty()) {
                        updateState(SearchFragmentState.NoResult)
                    }
                }
        }
    }

    fun searchWithDebounce(text: String) {
        latestSearchText = text
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(SEARCH_DEBOUNCE_DELAY)
            searchResult(text)
        }
    }

    fun clickDebounce(): Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            viewModelScope.launch {
                delay(CLICK_DEBOUNCE_DELAY)
                isClickAllowed = true
            }
        }
        return current
    }
    fun onLastItemReached(){






    }
}
