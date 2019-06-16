package com.shapeapp.shape.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shapeapp.shape.R
import com.shapeapp.shape.ui.recyclerviews.adapters.SmallCardRecyclerViewAdapter
import com.shapeapp.shape.data.repositories.CardsRepository

/**
 * Shows list of "My public shares" cards
 */
class MyPublicSharesDialog : DialogFragment() {

    //  TODO: delete (replaced with [MyPublicSharesBSD])

    private lateinit var viewModel: MyPublicSharesDialogViewModel
    private val cardRecyclerViewAdapter = SmallCardRecyclerViewAdapter(emptyArray())

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        configureViewModel()

        val inflatedView = getDialogInflatedView()
        configureRecyclerViewInView(inflatedView)

        val createdDialog = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setView(inflatedView)
                .setCancelable(false)
            builder.create()
        } ?: throw IllegalStateException("Activity can not be null")

        changeBackgroundToTransparent(createdDialog)
        changePositionToBottom(createdDialog)

        return createdDialog
    }

    private fun configureViewModel() {
        val cardRepository = CardsRepository
        val viewModelFactory = MyPublicSharesDialogViewModelFactory(cardRepository)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MyPublicSharesDialogViewModel::class.java)
        viewModel.myPublicSharesCards.observe(this, Observer { cards ->
            cardRecyclerViewAdapter.myDataset = cards.toTypedArray()
            cardRecyclerViewAdapter.notifyDataSetChanged()
        })
    }

    /**
     * Gets inflated [View] for [MyPublicSharesDialog] layout
     *
     * Contains workaround for StackOverflowError when getting [LayoutInflater]
     * See: https://stackoverflow.com/a/15152788
     * See: https://stackoverflow.com/a/20995083
     */
    private fun getDialogInflatedView(): View {
        // workaround to get [LayoutInflater]
        val layoutInflaterFromSystem = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        //  return inflated View
        return layoutInflaterFromSystem.inflate(R.layout.dialog_public_shares, null)
    }

    private fun configureRecyclerViewInView(inflatedView: View) {
        val recyclerView = inflatedView.findViewById<RecyclerView>(R.id.cards_list_recyclerview)
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = cardRecyclerViewAdapter
        }
    }

    /**
     * Set the background of [AlertDialog]'s root view to transparent
     * See: https://stackoverflow.com/a/28937224
     */
    private fun changeBackgroundToTransparent(createdDialog: AlertDialog) {
        createdDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    /**
     * Make the [AlertDialog] appear at the bottom of the screen
     */
    private fun changePositionToBottom(createdDialog: AlertDialog) {
        createdDialog.window?.attributes?.gravity = Gravity.BOTTOM
    }
}