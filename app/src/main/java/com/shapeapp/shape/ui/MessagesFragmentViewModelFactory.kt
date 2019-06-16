package com.shapeapp.shape.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shapeapp.shape.repositories.MessagesRepository

/**
 * Knows how to initiate [MessagesFragmentViewModel]
 */
class MessagesFragmentViewModelFactory(private val messagesRepository: MessagesRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MessagesFragmentViewModel(messagesRepository) as T
    }
}