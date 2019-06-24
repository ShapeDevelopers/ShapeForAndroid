package com.shapeapp.shape.ui.dialogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shapeapp.shape.data.repositories.CardRepositoryInterface

/**
 * Knows how to initiate [MyPublicSharesViewModel]
 */
class MyPublicSharesViewModelFactory(private val cardRepository: CardRepositoryInterface) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyPublicSharesViewModel(cardRepository) as T
    }
}