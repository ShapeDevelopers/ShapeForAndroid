package com.shapeapp.shape.recyclerviewadapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shapeapp.shape.R
import com.shapeapp.shape.data.Card
import com.shapeapp.shape.recyclerviewinterfaces.RecyclerViewCardClickListener

/**
 * Feeds [RecyclerView] with [Card] data
 */
class SmallCardRecyclerViewAdapter(var myDataset: Array<Card>) :
    RecyclerView.Adapter<SmallCardRecyclerViewAdapter.MyViewHolder>() {

    /**
     * Listener that will be informed about user click on a card
     */
    var cardClickListener: RecyclerViewCardClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val materialCardView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.small_card, parent, false)
        return MyViewHolder(materialCardView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nickname_textview.text = myDataset[holder.adapterPosition].senderNickname
        val cardImageUri = Uri.parse(myDataset[holder.adapterPosition].imageUrl)
        holder.background_imageview.setImageURI(cardImageUri)
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }


    inner class MyViewHolder(materialCardView: View) : RecyclerView.ViewHolder(materialCardView),
        View.OnClickListener {

        var nickname_textview: TextView = materialCardView.findViewById(R.id.nickname_textview)
        var background_imageview: ImageView = materialCardView.findViewById(R.id.background_imageview)

        init {
            materialCardView.setOnClickListener(this)
        }

        /**
         * Called when user clicks on item in [MyViewHolder]
         */
        override fun onClick(view: View) {
            val clickedCard = myDataset[adapterPosition]
            //  if there is listener, inform it that user has clicked on an item
            cardClickListener?.onCardClick(clickedCard, view)
        }

    }


}