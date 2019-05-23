package com.shapeapp.shape.mockupsmakers

import java.util.*

/**
 * Generates URI to random drawables
 */
object DrawablesMockups {

    private const val PACKAGE_NAME = "com.shapeapp.shape"

    fun getRandomDrawableUriString(): String {
        val randomNumber = Random().nextInt(3)
        val drawableName: String = when (randomNumber) {
            0 -> "mockup_golden_retriever"
            1 -> "mockup_norwegian_cat"
            else -> "mockup_fennec_fox"
        }

        return "android.resource://$PACKAGE_NAME/drawable/$drawableName"
    }

}