package com.shapeapp.shape.ui.receivedimage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shapeapp.shape.data.Card

/**
 * Knows how to initiate [ReceivedImageViewModel]
 */
class ReceivedImageFragmentViewModelFactory(private val card: Card) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReceivedImageViewModel(card) as T
    }
}