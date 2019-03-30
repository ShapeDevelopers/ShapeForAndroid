package com.shapeapp.shape.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shapeapp.shape.repositories.Repository

/**
 * Knows how to initiate [PublicFragmentViewModel]
 */
class PublicFragmentViewModelFactory(private val cardRepository: Repository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PublicFragmentViewModel(cardRepository) as T
    }
}