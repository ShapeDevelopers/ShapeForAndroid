package com.shapeapp.shape.dialogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shapeapp.shape.repositories.CardsRepository

/**
 * Knows how to initiate [MyPublicSharesDialogViewModel]
 */
class MyPublicSharesDialogViewModelFactory(private val cardRepository: CardsRepository) :
    ViewModelProvider.NewInstanceFactory() {

    //  TODO: delete ([MyPublicSharesDialog] was replaced with [MyPublicSharesBSD])

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyPublicSharesDialogViewModel(cardRepository) as T
    }
}