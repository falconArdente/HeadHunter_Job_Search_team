package ru.practicum.android.diploma.search.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.search.domain.api.SearchInteractor
import ru.practicum.android.diploma.search.presentation.state.SearchFragmentState

private const val SEARCH_DEBOUNCE_DELAY = 2000L
private const val CLICK_DEBOUNCE_DELAY = 1000L

class SearchViewModel(private val interactor: SearchInteractor) : ViewModel() {
    private val searchLiveData =
        MutableLiveData<SearchFragmentState>(SearchFragmentState.NoTextInInputEditText)
    private var latestSearchText: String? = null
    private var searchJob: Job? = null
    private var isClickAllowed = true

    fun fragmentStateLiveData(): LiveData<SearchFragmentState> = searchLiveData

    init {
        updateState(SearchFragmentState.NoTextInInputEditText)

    }

    fun updateState(state: SearchFragmentState) {
        searchLiveData.postValue(state)
    }

    private val searchJobDetails: Job? = null
    private fun searchResult(text: String) {
        searchJobDetails?.cancel()
        updateState(SearchFragmentState.Loading)
        Log.d("ROUTE__", text)
        Log.d("вм серчРез ", searchLiveData.value.toString())
        searchJobDetails != viewModelScope.launch {
            interactor
                .searchVacancy(text)
                .collect { vacancy ->
                    Log.d("ROUTE__", "searchVacancy ${vacancy.result}")
                    Log.d("коллект ", searchLiveData.value.toString())
                    Log.d("вэк ту стринг ", vacancy.toString())
                    if (vacancy.result!!.isNotEmpty()) {
                        updateState(SearchFragmentState.SearchVacancy(vacancy.result))
                        Log.d("нот эмпт ", vacancy.toString())
                    } else if (vacancy.result.isEmpty()) {
                        updateState(SearchFragmentState.NoResult)
                    } else if (vacancy.errorMessage != null) {
                        Log.d("еррор ", vacancy.toString())
                        updateState(SearchFragmentState.ServerError)
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
}
