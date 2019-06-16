package com.shapeapp.shape.recyclerviewadapters

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shapeapp.shape.R
import com.shapeapp.shape.data.entities.Message
import com.shapeapp.shape.mockupsmakers.DrawablesMockups
import com.shapeapp.shape.recyclerviewinterfaces.RecyclerViewMessageClickListener

/**
 * Feeds [RecyclerView] with previewed [Message] data
 *
 * If you need to be informed about clicks, set [messageClickListener]
 */
class MessagesPreviewRecyclerViewAdapter(var messagesDataset: List<Message>) :
    RecyclerView.Adapter<MessagesPreviewRecyclerViewAdapter.MyViewHolder>() {

    var messageClickListener: RecyclerViewMessageClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val messageView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.message_item_preview, parent, false)
        return MyViewHolder(messageView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.apply {
            //  TODO: make own random drawable for message or move method from [CardMockups] outside it
            //  TODO: load real avatar based on nickname (?)
            senderAvatar.setImageURI(Uri.parse(DrawablesMockups.getRandomDrawableUriString()))
            senderNickname.text = messagesDataset[adapterPosition].senderNickname
            intro.text = messagesDataset[adapterPosition].textContent
            val fixClippingItalicTextAtRightEdge = "\u00A0" // Unicode NO-BREAK SPACE
            fullDate.text = messagesDataset[adapterPosition].dateStampFull + fixClippingItalicTextAtRightEdge
        }
    }

    override fun getItemCount(): Int {
        return messagesDataset.size
    }


    inner class MyViewHolder(messageView: View) : RecyclerView.ViewHolder(messageView), View.OnClickListener {

        var senderAvatar: ImageView = messageView.findViewById(R.id.sender_avatar_imageview)
        var senderNickname: TextView = messageView.findViewById(R.id.sender_nickname_textview)
        var intro: TextView = messageView.findViewById(R.id.intro_textview)
        var fullDate: TextView = messageView.findViewById(R.id.full_date_textview)

        init {
            messageView.setOnClickListener(this)
        }

        /**
         * Called when user clicks on item in [MyViewHolder]
         */
        override fun onClick(view: View) {
            val clickedMessage = messagesDataset[adapterPosition]
            //  if there is listener, inform it that user has clicked on an item
            messageClickListener?.onMessageClick(clickedMessage, view)
        }

    }

}