package com.shapeapp.shape.ui.recyclerviews.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shapeapp.shape.R
import com.shapeapp.shape.data.database.entities.Message
import com.shapeapp.shape.internal.mockupsmakers.DrawablesMockups

/**
 * Feeds [RecyclerView] with full [Message] data
 */
class MessagesFullRecyclerViewAdapter(var messagesDataset: List<Message>) :
    RecyclerView.Adapter<MessagesFullRecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val messageView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.message_full_item, parent, false)
        return MyViewHolder(messageView)
    }

    override fun getItemCount(): Int {
        return messagesDataset.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.apply {
            senderNickname.text = messagesDataset[adapterPosition].senderNickname
            //  TODO: load real avatar
            val senderAvatarUri = Uri.parse(DrawablesMockups.getRandomDrawableUriString())
            val context = senderAvatar.context
            Glide.with(context)
                .load(senderAvatarUri)
                .into(senderAvatar)
            text.text = messagesDataset[adapterPosition].textContent
            fullDate.text = messagesDataset[adapterPosition].dateStampFull
        }
    }


    inner class MyViewHolder(messageView: View) : RecyclerView.ViewHolder(messageView) {

        var senderNickname: TextView = messageView.findViewById(R.id.sender_nickname_textview)
        var senderAvatar: ImageView = messageView.findViewById(R.id.sender_avatar_imageview)
        var text: TextView = messageView.findViewById(R.id.text_textview)
        var fullDate: TextView = messageView.findViewById(R.id.full_date_textview)


    }

}