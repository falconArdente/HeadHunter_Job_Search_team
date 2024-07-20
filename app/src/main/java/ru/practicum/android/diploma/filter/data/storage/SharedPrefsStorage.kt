package ru.practicum.android.diploma.filter.data.storage

import android.content.SharedPreferences
import com.google.gson.Gson
import ru.practicum.android.diploma.filter.domain.model.FilterGeneral

const val FILTER_KEY = "saved_filter"

class SharedPrefsStorage(private val sharedPreferences: SharedPreferences, private val gson: Gson) : FilterStorage {
    override fun saveFilterParameters(filter: FilterGeneral) {
        val json = gson.toJson(filter)
        sharedPreferences.edit().putString(FILTER_KEY, json).apply()
    }

    override fun getFilterParameters(): FilterGeneral {
        val json = sharedPreferences.getString(FILTER_KEY, null)
            ?: return FilterGeneral()
        return gson.fromJson(json, FilterGeneral::class.java)
    }

    override fun clearAllFilterParameters() {
        val editor = sharedPreferences.edit()
        editor.remove(FILTER_KEY).apply()
    }
}
