package com.shapeapp.shape.recyclerviewinterfaces

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Interface definition for a callback to be invoked when a card in [RecyclerView] is clicked.
 */
interface RecyclerViewCardClickListener {

    fun onCardClick(cardExtraText: String, cardItemView: View)

}