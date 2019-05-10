package com.shapeapp.shape.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shapeapp.shape.R
import com.shapeapp.shape.recyclerviewadapters.SmallCardRecyclerViewAdapter
import com.shapeapp.shape.repositories.Repository
import kotlinx.android.synthetic.main.bsd_my_public_shares.*

class MyPublicSharesBSD : BottomSheetDialogFragment() {

    private lateinit var viewModel: MyPublicSharesBSDViewModel
    private val cardRecyclerViewAdapter = SmallCardRecyclerViewAdapter(emptyArray())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bsd_my_public_shares, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configureViewModel()
        configureRecyclerView()
    }

    private fun configureViewModel() {
        val cardRepository = Repository
        val viewModelFactory = MyPublicSharesBSDViewModelFactory(cardRepository)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MyPublicSharesBSDViewModel::class.java)
        viewModel.myPublicSharesCards.observe(this, Observer { cards ->
            cardRecyclerViewAdapter.myDataset = cards.toTypedArray()
            cardRecyclerViewAdapter.notifyDataSetChanged()
        })
    }

    private fun configureRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        cards_list_recyclerview.apply {
            layoutManager = linearLayoutManager
            adapter = cardRecyclerViewAdapter
        }
    }
}