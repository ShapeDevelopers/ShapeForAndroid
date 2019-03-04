package com.shapeapp.shape.mockupsmakers

import android.content.Context
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import com.google.android.material.card.MaterialCardView
import com.shapeapp.shape.R

//  TODO: delete this (old code)

/**
 * Shows how to programmatically create [MaterialCardView] that consists of:
 * [LinearLayout] with [ImageView]
 */
object MockupCardMaker {

    /**
     * Use this function to create filled [MaterialCardView]
     */
    fun getFilledCard(context: Context): MaterialCardView {
        val card = createCard(context)
        val image = createImage(context)
        val linearLayout = createLinearLayout(context)

        linearLayout.addView(image)
        card.addView(linearLayout)

        return card
    }

    private fun createCard(context: Context): MaterialCardView {
        val width = context.resources.getDimensionPixelSize(R.dimen.small_card_width)
        val height = context.resources.getDimensionPixelSize(R.dimen.small_card_height)
        val layoutParams = LinearLayout.LayoutParams(width, height)

        val margin = context.resources.getDimensionPixelSize(R.dimen.small_card_margin)
        layoutParams.setMargins(margin)

        val card = MaterialCardView(context)
        card.layoutParams = layoutParams
        card.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent))
        val radius = context.resources.getDimensionPixelSize(R.dimen.small_card_corner_radius).toFloat()
        card.radius = radius

        return card
    }

    private fun createImage(context: Context): ImageView {
        val image = ImageView(context)

        val layoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        image.layoutParams = layoutParams
        image.scaleType = ImageView.ScaleType.CENTER_CROP
        image.setImageResource(R.drawable.near_me_icon)

        return image
    }

    private fun createLinearLayout(context: Context): LinearLayout {
        val linearLayout = LinearLayout(context)

        val layoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        linearLayout.layoutParams = layoutParams
        linearLayout.orientation = LinearLayout.HORIZONTAL

        return linearLayout
    }

}