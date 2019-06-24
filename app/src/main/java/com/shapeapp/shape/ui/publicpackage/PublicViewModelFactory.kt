package com.shapeapp.shape.ui.publicpackage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shapeapp.shape.data.repositories.CardRepositoryInterface

/**
 * Knows how to initiate [PublicViewModel]
 */
class PublicViewModelFactory(private val cardRepository: CardRepositoryInterface) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PublicViewModel(cardRepository) as T
    }
}