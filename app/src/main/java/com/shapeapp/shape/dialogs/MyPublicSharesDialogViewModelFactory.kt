package com.shapeapp.shape.dialogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shapeapp.shape.repositories.Repository

/**
 * Knows how to initiate [MyPublicSharesDialogViewModel]
 */
class MyPublicSharesDialogViewModelFactory(private val cardRepository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyPublicSharesDialogViewModel(cardRepository) as T
    }
}