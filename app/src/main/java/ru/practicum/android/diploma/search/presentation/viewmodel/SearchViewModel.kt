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
    private var searchJob: Job? = null
    private var isClickAllowed = true
    private var suggestionsList = MutableLiveData<List<String>>(emptyList())
    val suggestionsLivaData: LiveData<List<String>> = suggestionsList
    private val filterIsOn = MutableLiveData(false)
    val filterStateToObserve: LiveData<Boolean> = filterIsOn
    private var parametersForSearch: SearchParameters? = null

    var currentPage = 0
    private var maxPages = 0
    private val vacanciesList = mutableListOf<Vacancy>()
    private var totalFound = 0

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

    private fun updateState(searchVacancy: List<Vacancy>, totalFoundVacancy: Int) {
        searchLiveData.postValue(
            when (searchLiveData.value) {
                is SearchFragmentState.SearchVacancy -> {
                    (searchLiveData.value as SearchFragmentState.SearchVacancy)
                        .copy(searchVacancy = searchVacancy, totalFoundVacancy = totalFoundVacancy)
                }

                else -> {
                    SearchFragmentState.SearchVacancy(
                        searchVacancy = searchVacancy,
                        totalFoundVacancy = totalFoundVacancy
                    )
                }
            }
        )
    }

    private val searchJobDetails: Job? = null
    private fun searchResult(text: String) {
        searchJobDetails?.cancel()
        updateState(SearchFragmentState.Loading)
        searchJobDetails != viewModelScope.launch {
            interactor
                .searchVacancy(text, parametersForSearch, PER_PAGE, currentPage)
                .collect { vacancy ->
                    if (vacancy.result!!.isNotEmpty()) {
                        maxPages = vacancy.pages
                        if (currentPage == maxPages || vacanciesList.count() == vacancy.foundVacancy) {
                            vacanciesList.addAll(vacancy.result)
                            updateState(searchVacancy = vacanciesList, totalFoundVacancy = totalFound)
                        }
                        if (currentPage < maxPages) {
                            vacanciesList += vacancy.result
                        }
                        totalFound = vacancy.foundVacancy
                        updateState(searchVacancy = vacanciesList, totalFoundVacancy = totalFound)
                    } else if (vacancy.errorMessage!!.isNotEmpty()) {
                        updateState(SearchFragmentState.ServerError)
                    } else if (vacancy.errorMessage.isNullOrEmpty()) {
                        updateState(SearchFragmentState.NoResult)
                    }
                }
        }
    }

    fun searchWithDebounce(text: String) {
        //  currentPage = 0
        vacanciesList.clear()
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

    fun onLastItemReached() {
        if (currentPage == maxPages) {
                updateState(
                   (searchLiveData.value as SearchFragmentState.SearchVacancy)
                       .copy(searchVacancy = vacanciesList, totalFoundVacancy = totalFound)
              )
        //   updateState(SearchFragmentState.SearchVacancy(vacanciesList, totalFound))
       // с закомиченым не вылетает
        }
        if (currentPage < maxPages) {
            currentPage++
        }
        if (currentPage < maxPages) {
            updateState(SearchFragmentState.Loading)
            searchResult(latestSearchText!!)
        }
    }
}

