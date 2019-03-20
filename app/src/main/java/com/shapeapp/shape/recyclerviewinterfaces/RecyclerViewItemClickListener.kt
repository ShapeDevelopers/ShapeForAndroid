package com.shapeapp.shape.recyclerviewinterfaces

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Interface definition for a callback to be invoked when an item in [RecyclerView] is clicked.
 */
interface RecyclerViewItemClickListener {

    fun onItemClick(itemPosition: Int, itemView: View)
}