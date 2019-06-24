package com.shapeapp.shape.ui.dialogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shapeapp.shape.data.database.entities.Card
import com.shapeapp.shape.data.repositories.CardRepositoryInterface

/**
 * [ViewModel] for [MyPublicSharesBSD]
 */
class MyPublicSharesViewModel(private val cardRepository: CardRepositoryInterface) : ViewModel() {

    var myPublicSharesCards: LiveData<List<Card>>
        private set

    init {
        myPublicSharesCards = cardRepository.getPublicSharesCards()
    }

}