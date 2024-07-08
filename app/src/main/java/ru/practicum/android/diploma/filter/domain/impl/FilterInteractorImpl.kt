package ru.practicum.android.diploma.filter.domain.impl

import ru.practicum.android.diploma.filter.data.storage.SharedPrefsStorage
import ru.practicum.android.diploma.filter.domain.api.FilterInteractor
import ru.practicum.android.diploma.filter.domain.model.Filter

class FilterInteractorImpl(private val sharedPrefsStorage: SharedPrefsStorage) : FilterInteractor {
    override fun saveFilter(filter: Filter) {
        sharedPrefsStorage.saveFilter(filter)
    }

    override fun getFilter(): Filter = sharedPrefsStorage.getFilter()
}
