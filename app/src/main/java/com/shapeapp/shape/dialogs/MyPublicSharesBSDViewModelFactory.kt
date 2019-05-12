package com.shapeapp.shape.dialogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shapeapp.shape.repositories.CardsRepository

/**
 * Knows how to initiate [MyPublicSharesBSDViewModel]
 */
class MyPublicSharesBSDViewModelFactory(private val cardRepository: CardsRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyPublicSharesBSDViewModel(cardRepository) as T
    }
}