package ru.practicum.android.diploma.search.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.search.domain.api.GetFilterUseCase
import ru.practicum.android.diploma.search.domain.api.GetSuggestionsForSearchUseCase
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.domain.model.SearchParameters
import ru.practicum.android.diploma.search.domain.model.Vacancy
import ru.practicum.android.diploma.search.presentation.state.SearchFragmentState

private const val SEARCH_DEBOUNCE_DELAY = 2000L
private const val CLICK_DEBOUNCE_DELAY = 1000L
private const val PER_PAGE = 20

class SearchViewModel(
    private val interactor: SearchInteractor,
    private val getSuggestsUseCase: GetSuggestionsForSearchUseCase,
    private val getFilterUseCase: GetFilterUseCase,
) : ViewModel() {
    private val searchLiveData =
        MutableLiveData<SearchFragmentState>(SearchFragmentState.NoTextInInputEditText)
    private var latestSearchText: String? = null
    private var autoSearchJob: Job? = null
    private var isClickAllowed = true
    private var suggestionsList = MutableLiveData<List<String>>(emptyList())
    val suggestionsLivaData: LiveData<List<String>> = suggestionsList
    private val filterIsOn = MutableLiveData(false)
    val filterStateToObserve: LiveData<Boolean> = filterIsOn
    private var parametersForSearch: SearchParameters? = null
    private var searchInProcess = false

    var currentPage = 0
    private var maxPages = 0
    private val vacanciesList = mutableListOf<Vacancy>()
    private var totalFound = 0
    private var isLastCapitalOfInputSearched = false

    init {
        updateState(SearchFragmentState.NoTextInInputEditText)
    }

    fun checkFilterStatus() {
        parametersForSearch = getFilterUseCase.execute()
        filterIsOn.postValue(parametersForSearch != null)
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

    private fun updateState(searchVacancy: List<Vacancy>, totalFoundVacancy: Int, isLastPage: Boolean) {
        searchLiveData.postValue(
            when (searchLiveData.value) {
                is SearchFragmentState.SearchVacancy -> {
                    (searchLiveData.value as SearchFragmentState.SearchVacancy)
                        .copy(
                            searchVacancy = searchVacancy,
                            totalFoundVacancy = totalFoundVacancy,
                            isLastPage = isLastPage
                        )
                }

                else -> {
                    SearchFragmentState.SearchVacancy(
                        searchVacancy = searchVacancy,
                        totalFoundVacancy = totalFoundVacancy,
                        isLastPage = isLastPage
                    )
                }
            }
        )
    }

    private var searchJobDetails: Job? = null
    private fun searchResult(text: String?) {
        searchJobDetails?.cancel()
        if (text?.isBlank() != false || isLastCapitalOfInputSearched) return

        if (currentPage == 0) updateState(SearchFragmentState.Loading)
        searchJobDetails = viewModelScope.launch {
            searchInProcess = true
            interactor
                .searchVacancy(text, parametersForSearch, PER_PAGE, currentPage)
                .collect { vacancy ->
                    when {
                        vacancy.result!!.isNotEmpty() -> {
                            maxPages = vacancy.pages
                            totalFound = vacancy.foundVacancy
                            if (currentPage == maxPages - 1 || vacanciesList.count() == vacancy.foundVacancy) {
                                vacanciesList.addAll(vacancy.result)
                                updateState(
                                    searchVacancy = vacanciesList,
                                    totalFoundVacancy = vacancy.foundVacancy,
                                    isLastPage = true
                                )
                            } else {
                                vacanciesList += vacancy.result
                                updateState(
                                    searchVacancy = vacanciesList,
                                    totalFoundVacancy = vacancy.foundVacancy,
                                    isLastPage = maxPages == 1
                                )
                            }
                        }

                        vacancy.errorMessage!!.isNotEmpty() -> {
                            updateState(SearchFragmentState.ServerError(vacancy.result))
                        }

                        else -> updateState(SearchFragmentState.NoResult)
                    }
                }
            searchInProcess = false
        }
    }

    fun searchImmidiently(text: String) {
        vacanciesList.clear()
        autoSearchJob?.cancel()
        searchResult(text)
    }

    fun repeatSearch() {
        if (!latestSearchText.isNullOrEmpty()) searchImmidiently(latestSearchText!!)
    }

    fun searchWithDebounce(text: String?) {
        if (text?.isBlank() != false) {
            searchLiveData.postValue(SearchFragmentState.NoTextInInputEditText)
            isLastCapitalOfInputSearched = true
        } else {
            isLastCapitalOfInputSearched = false
            vacanciesList.clear()
            updateState(SearchFragmentState.Loading)
            latestSearchText = text
            autoSearchJob?.cancel()
            autoSearchJob = viewModelScope.launch {
                delay(SEARCH_DEBOUNCE_DELAY)
                searchResult(text)
            }
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

    fun onLastItemReached() {
        if (currentPage < maxPages - 1 && !searchInProcess) {
            currentPage++
            autoSearchJob?.cancel()
            autoSearchJob = viewModelScope.launch {
                searchResult(latestSearchText!!)
            }
        } else {
            updateState(SearchFragmentState.SearchVacancy(vacanciesList, totalFound, isLastPage = true))
        }
    }
}
