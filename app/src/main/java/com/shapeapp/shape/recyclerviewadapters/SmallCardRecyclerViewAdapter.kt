package com.shapeapp.shape.recyclerviewadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.shapeapp.shape.R
import com.shapeapp.shape.recyclerviewinterfaces.RecyclerViewItemClickListener
import kotlinx.android.synthetic.main.small_card.view.*

class SmallCardRecyclerViewAdapter(var myDataset: Array<String>) :
    RecyclerView.Adapter<SmallCardRecyclerViewAdapter.MyViewHolder>() {

    /**
     * Listener that will be informed about user click on item
     */
    var recyclerViewItemClickListener: RecyclerViewItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val materialCardView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.small_card, parent, false) as MaterialCardView
        return MyViewHolder(materialCardView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.materialCardView.nickname_textview.text = myDataset[holder.adapterPosition]
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }


    inner class MyViewHolder(val materialCardView: MaterialCardView) : RecyclerView.ViewHolder(materialCardView),
        View.OnClickListener {

        init {
            materialCardView.setOnClickListener(this)
        }

        /**
         * Called when user clicks on item in [MyViewHolder]
         */
        override fun onClick(view: View) {
            //  if there is listener, inform it that user has clicked on an item
            recyclerViewItemClickListener?.onItemClick(adapterPosition, view)
        }

    }


}