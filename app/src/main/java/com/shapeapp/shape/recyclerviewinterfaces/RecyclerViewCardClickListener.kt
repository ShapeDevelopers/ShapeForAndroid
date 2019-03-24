package com.shapeapp.shape.recyclerviewinterfaces

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.shapeapp.shape.data.Card

/**
 * Interface definition for a callback to be invoked when a card in [RecyclerView] is clicked.
 */
interface RecyclerViewCardClickListener {

    fun onCardClick(clickedCard: Card, cardItemView: View)

}