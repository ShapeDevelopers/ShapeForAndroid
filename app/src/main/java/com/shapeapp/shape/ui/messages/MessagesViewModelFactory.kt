package com.shapeapp.shape.ui.messages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shapeapp.shape.data.repositories.MessagesRepositoryObsolete

/**
 * Knows how to initiate [MessagesViewModel]
 */
class MessagesViewModelFactory(private val messagesRepository: MessagesRepositoryObsolete) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MessagesViewModel(messagesRepository) as T
    }
}