package com.shapeapp.shape.ui.gesturesdetection

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener

/**
 * Listener that allows detection of swipe gestures: right, left, top, bottom
 * To use it override selected methods:
 * [onSwipeRight]
 * [onSwipeLeft]
 * [onSwipeTop]
 * [onSwipeBottom]
 * with your actions
 *
 * Based on: https://stackoverflow.com/questions/4139288/android-how-to-handle-right-to-left-swipe-gestures/12938787#12938787
 */
open class OnFourWaysSwipeListener(context: Context) : OnTouchListener {

    /**
     * Thresholds for distance and velocity
     */
    private companion object {
        private const val SWIPE_DISTANCE_THRESHOLD = 200
        private const val SWIPE_VELOCITY_THRESHOLD = 100
    }

    private val gestureDetector: GestureDetector

    init {
        gestureDetector = GestureDetector(context, GestureListener())
    }

    /**
     * Inner class that triggers proper swipe method of its outer class
     */
    private inner class GestureListener : SimpleOnGestureListener() {

        /**
         * Triggered when fling event occurs
         *
         * @return True if the event is consumed, otherwise false
         */
        override fun onFling(event1: MotionEvent, event2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            var wasEventConsumed = false

            val differenceY = event2.y - event1.y
            val differenceX = event2.x - event1.x

            when (determineAxis(differenceX, differenceY)) {
                Axis.HORIZONTAL -> {
                    if (areThresholdsFulfilled(differenceX, velocityX)) {
                        when {
                            differenceX > 0 -> onSwipeRight()
                            else -> onSwipeLeft()
                        }
                        wasEventConsumed = true
                    }

                }
                Axis.VERTICAL -> {
                    if (areThresholdsFulfilled(differenceY, velocityY)) {
                        when {
                            differenceY > 0 -> onSwipeBottom()
                            else -> onSwipeTop()
                        }
                        wasEventConsumed = true
                    }
                }
                Axis.DIAGONAL -> {
                    wasEventConsumed = false
                }
            }

            return wasEventConsumed
        }

        private fun determineAxis(differenceX: Float, differenceY: Float): Axis {
            return when {
                Math.abs(differenceX) > Math.abs(differenceY) -> Axis.HORIZONTAL
                Math.abs(differenceX) < Math.abs(differenceY) -> Axis.VERTICAL
                else -> Axis.DIAGONAL
            }
        }

        private fun areThresholdsFulfilled(distanceDifference: Float, velocity: Float): Boolean {
            val absDistanceDifference = Math.abs(distanceDifference)
            val absVelocity = Math.abs(velocity)
            return (absDistanceDifference > SWIPE_DISTANCE_THRESHOLD && absVelocity > SWIPE_VELOCITY_THRESHOLD)
        }

        /**
         * Triggered when tap occurs
         */
        override fun onDown(e: MotionEvent): Boolean {
            //  return true, because tap was consumed
            return true
        }

    }

    private enum class Axis {
        HORIZONTAL,
        VERTICAL,
        DIAGONAL
    }

    /**
     * Method that can be overridden to handle swipe
     */
    open fun onSwipeRight() {}

    /**
     * Method that can be overridden to handle swipe
     */
    open fun onSwipeLeft() {}

    /**
     * Method that can be overridden to handle swipe
     */
    open fun onSwipeTop() {}

    /**
     * Method that can be overridden to handle swipe
     */
    open fun onSwipeBottom() {}


    final override fun onTouch(v: View, event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

}