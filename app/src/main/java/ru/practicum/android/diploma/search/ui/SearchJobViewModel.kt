package ru.practicum.android.diploma.search.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.search.domain.api.GetSuggestionsForSearchUseCase

class SearchJobViewModel(private val getSuggestsUseCase: GetSuggestionsForSearchUseCase, context: Context) :
    ViewModel() {
    private var suggestionsList = MutableLiveData<List<String>>(emptyList())
    val suggestionsLivaData: LiveData<List<String>> = suggestionsList
    fun getSuggestionsForSearch(textForSuggests: String) {
        if (textForSuggests.length in 2..3000)
            viewModelScope.launch {
                getSuggestsUseCase.execute(textForSuggests)
                    .collect {
                        suggestionsList.postValue(it)
                    }
            }
    }
}

