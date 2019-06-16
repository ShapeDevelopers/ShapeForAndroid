package com.shapeapp.shape.ui.messages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shapeapp.shape.repositories.MessagesRepository

/**
 * Knows how to initiate [MessagesViewModel]
 */
class MessagesFragmentViewModelFactory(private val messagesRepository: MessagesRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MessagesViewModel(messagesRepository) as T
    }
}