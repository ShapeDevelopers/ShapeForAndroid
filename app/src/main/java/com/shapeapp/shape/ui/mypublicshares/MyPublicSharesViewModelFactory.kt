package com.shapeapp.shape.ui.mypublicshares

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shapeapp.shape.data.repositories.card.CardRepository

/**
 * Knows how to initiate [MyPublicSharesViewModel]
 */
class MyPublicSharesViewModelFactory(private val cardRepository: CardRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyPublicSharesViewModel(cardRepository) as T
    }
}