package com.shapeapp.shape.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shapeapp.shape.data.Card

/**
 * Knows how to initiate [ReceivedImageFragmentViewModel]
 */
class ReceivedImageFragmentViewModelFactory(private val card: Card) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReceivedImageFragmentViewModel(card) as T
    }
}