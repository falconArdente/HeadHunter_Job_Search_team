package ru.practicum.android.diploma.favorites.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.favorites.domain.api.GetFavoritesListUseCase
import ru.practicum.android.diploma.favorites.presentation.state.FavoritesListState

class FavoritesViewModel(private val getFavoritesListUseCase: GetFavoritesListUseCase) : ViewModel() {
    private val mutableStateLiveData = MutableLiveData<FavoritesListState>(FavoritesListState.Empty)
    val stateToObserve: LiveData<FavoritesListState> = mutableStateLiveData
    fun openForDetails(vacancyId: String) {

    }
}
