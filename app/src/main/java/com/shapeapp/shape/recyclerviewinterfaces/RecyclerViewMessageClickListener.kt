package com.shapeapp.shape.recyclerviewinterfaces

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.shapeapp.shape.data.Message

/**
 * Interface definition for a callback to be invoked when a message in [RecyclerView] is clicked.
 */
interface RecyclerViewMessageClickListener {

    fun onMessageClick(clickedMessage: Message, messageItemView: View)

}