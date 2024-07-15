package ru.practicum.android.diploma.favorites.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.details.domain.model.VacancyDetails
import ru.practicum.android.diploma.details.ui.JobDetailsFragment
import ru.practicum.android.diploma.favorites.domain.api.GetFavoritesListUseCase
import ru.practicum.android.diploma.favorites.presentation.state.FavoritesListState
import ru.practicum.android.diploma.utils.Resource

class FavoritesViewModel(private val getFavoritesListUseCase: GetFavoritesListUseCase) : ViewModel() {
    private val mutableStateLiveData = MutableLiveData<FavoritesListState>(FavoritesListState.Empty)
    val stateToObserve: LiveData<FavoritesListState> = mutableStateLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            runDataInteraction()
        }
    }

    fun openForDetails(vacancyId: String, navController: NavController) {
        navController.navigate(
            R.id.action_searchJobFragment_to_jobDetailsFragment,
            JobDetailsFragment.createArgs(vacancyId)
        )
    }

    private suspend fun runDataInteraction() {
        getFavoritesListUseCase.execute()
            .collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        mutableStateLiveData.postValue(FavoritesListState.Error)
                    }

                    is Resource.Success -> {
                        with(resource.data as List<VacancyDetails>) {
                            if (this.isEmpty()) {
                                mutableStateLiveData.postValue(FavoritesListState.Empty)
                            } else {
                                mutableStateLiveData.postValue(FavoritesListState.Success(this))
                            }
                        }
                    }
                }
            }
    }
}
