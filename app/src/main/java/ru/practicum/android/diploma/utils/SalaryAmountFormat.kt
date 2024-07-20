package ru.practicum.android.diploma.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun formatSalaryAmount(salaryAmount: Int?): String {
    val delimiterSymbol = DecimalFormatSymbols().apply { groupingSeparator = ' ' }
    val numberFormat = DecimalFormat("###,###,###,###,###", delimiterSymbol)
    return numberFormat.format(salaryAmount).toString()
}
