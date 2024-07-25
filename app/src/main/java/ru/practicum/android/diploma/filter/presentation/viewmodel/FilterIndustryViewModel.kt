package ru.practicum.android.diploma.filter.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filter.domain.impl.FilterDictionariesRepository
import ru.practicum.android.diploma.filter.domain.impl.FilterStorageRepository
import ru.practicum.android.diploma.filter.domain.model.Industry
import ru.practicum.android.diploma.filter.domain.model.IndustryFilter
import ru.practicum.android.diploma.filter.presentation.model.IndustryWithCheck
import ru.practicum.android.diploma.filter.presentation.state.FilterIndustryState
import ru.practicum.android.diploma.utils.NetworkStatus
import ru.practicum.android.diploma.utils.Resource

class FilterIndustryViewModel(
    private val filterStorage: FilterStorageRepository,
    private val filterDictionaries: FilterDictionariesRepository,
    private val networkStatus: NetworkStatus
) : ViewModel() {
    private var jobStorage: Job? = null
    private var jobDictionaries: Job? = null
    private var savedIndustry: Industry? = null

    private val filterState = MutableLiveData<FilterIndustryState>()
    fun getState(): LiveData<FilterIndustryState> = filterState

    fun loadIndustryList() {
        jobDictionaries?.cancel()

        filterState.postValue(FilterIndustryState.Loading)

        jobDictionaries = viewModelScope.launch(Dispatchers.IO) {
            if (networkStatus.isConnected()) {
                filterDictionaries.getIndustries().collect { response ->
                    when (response) {
                        is Resource.Success<*> -> {
                            if (response.data.isNullOrEmpty()) {
                                filterState.postValue(FilterIndustryState.EmptyList())
                            } else {
                                val listIndustries = getSingleList(response.data)
                                filterState.postValue(FilterIndustryState.LoadedList(listIndustries))
                            }
                        }

                        else -> {
                            filterState.postValue(FilterIndustryState.EmptyList())
                        }
                    }
                }
            } else {
                filterState.postValue(FilterIndustryState.NoInternetConnection)
            }
        }
    }

    private fun getSingleList(listIndustries: List<Industry>): List<IndustryWithCheck> {
        val finalList: MutableList<IndustryWithCheck> = mutableListOf()

        listIndustries.forEach { industry ->
            finalList.add(IndustryWithCheck(industry = industry))

            if (industry.industries.isNotEmpty()) {
                finalList.addAll(
                    industry.industries.map {
                        IndustryWithCheck(
                            industry = Industry(
                                id = it.id,
                                industries = emptyList(),
                                name = it.name
                            )
                        )
                    }
                )
            }
        }
        return finalList
    }

    fun saveSelectedIndustry(industry: Industry) {
        savedIndustry = industry
    }

    fun checkListIndustry(count: Int) {
        if (count == 0) {
            filterState.postValue(FilterIndustryState.EmptyList())
        } else {
            filterState.postValue(FilterIndustryState.Filtered)
        }
    }

    fun saveIndustry() {
        jobStorage?.cancel()

        jobStorage = viewModelScope.launch(Dispatchers.IO) {
            if (savedIndustry != null) {
                val industry = IndustryFilter(
                    industryId = savedIndustry!!.id,
                    industryName = savedIndustry!!.name
                )
                filterStorage.saveIndustry(industry)
            } else {
                filterStorage.saveIndustry(
                    IndustryFilter()
                )
            }

            filterState.postValue(FilterIndustryState.SavedFilter())
        }
    }

    fun setVisibleApply(isChecked: Boolean) {
        filterState.postValue(FilterIndustryState.ApplyVisible(isChecked))
    }
}
