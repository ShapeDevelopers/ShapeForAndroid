package com.shapeapp.shape.ui.publicpackage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shapeapp.shape.data.repositories.CardRepository

/**
 * Knows how to initiate [PublicViewModel]
 */
class PublicViewModelFactory(private val cardRepository: CardRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PublicViewModel(cardRepository) as T
    }
}