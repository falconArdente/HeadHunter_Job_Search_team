package ru.practicum.android.diploma.utils

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

fun Context.toPx(dp: Int): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp.toFloat(),
    resources.displayMetrics
)

fun convertDpToPixels(context: Context, dp: Float) = dp * context.resources.displayMetrics.density

fun exactDpToPx(context: Context, dp: Int): Int {
    val displayMetrics: DisplayMetrics = context.resources.displayMetrics
    return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
}

fun exactDpToPxHeightBased(context: Context, dp: Int): Int {
    val displayMetrics: DisplayMetrics = context.resources.displayMetrics
    return Math.round(dp * (displayMetrics.ydpi / DisplayMetrics.DENSITY_DEFAULT))
}

fun exactPxToDp(context: Context, px: Int): Int {
    val displayMetrics: DisplayMetrics = context.resources.displayMetrics
    return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
}

fun getCorrectionCoefficient(context: Context): Float {
    val displayMetrics: DisplayMetrics = context.resources.displayMetrics
    return ((displayMetrics.density - 2) / 0.25f) * 8.3f
}
