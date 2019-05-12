package com.shapeapp.shape.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shapeapp.shape.repositories.Repository

/**
 * Knows how to initiate [MessagesFragmentViewModel]
 */
class MessagesFragmentViewModelFactory(private val messagesRepository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MessagesFragmentViewModel(messagesRepository) as T
    }
}