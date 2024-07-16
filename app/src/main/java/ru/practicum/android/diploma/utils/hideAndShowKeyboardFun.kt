package ru.practicum.android.diploma.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.showKeyboard(context: Context) {
    val imm =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, 0)
}

fun View.hideKeyboard(context: Context) {
    val inputManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, 0)
}
