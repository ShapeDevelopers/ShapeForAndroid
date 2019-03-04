package com.shapeapp.shape.recyclerviewadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.shapeapp.shape.R
import kotlinx.android.synthetic.main.small_card.view.*

class SmallCardRecyclerViewAdapter(var myDataset: Array<String>) :
    RecyclerView.Adapter<SmallCardRecyclerViewAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val materialCardView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.small_card, parent, false) as MaterialCardView
        return MyViewHolder(materialCardView)

    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.materialCardView.nickname_textview.text = myDataset[position]
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    class MyViewHolder(val materialCardView: MaterialCardView) : RecyclerView.ViewHolder(materialCardView)


}