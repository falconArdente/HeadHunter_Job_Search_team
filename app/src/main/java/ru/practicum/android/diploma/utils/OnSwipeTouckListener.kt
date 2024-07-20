package ru.practicum.android.diploma.utils

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import kotlin.math.abs

const val SWIPE_THRESHOLD = 100

open class OnSwipeTouchListener(context: Context) : View.OnTouchListener {

    private var mDetector: GestureDetectorCompat

    init {
        mDetector = GestureDetectorCompat(context, GestureListener())
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        v!!.performClick()
        return mDetector.onTouchEvent(event!!)
    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            onPress()
            return true
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            var result = false
            try {
                val diffX = e2.x - (e1?.x ?: 0f)

                if (abs(diffX) > SWIPE_THRESHOLD){
                    if (diffX > 0) {
                        onSwipeRight()
                    }
                    else{
                        onSwipeLeft()
                    }

                }
                result = true
            }
             catch (exception: Exception) {
                exception.printStackTrace()
            }
            return result
        }
    }

    open fun onPress(){}

    open fun onSwipeRight() {}

    open fun onSwipeLeft() {}
}
