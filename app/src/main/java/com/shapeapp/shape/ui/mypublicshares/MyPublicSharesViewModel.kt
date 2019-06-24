package com.shapeapp.shape.ui.mypublicshares

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shapeapp.shape.data.database.entities.Card
import com.shapeapp.shape.data.repositories.card.CardRepository

/**
 * [ViewModel] for [MyPublicSharesBSD]
 */
class MyPublicSharesViewModel(private val cardRepository: CardRepository) : ViewModel() {

    var myPublicSharesCards: LiveData<List<Card>>
        private set

    init {
        myPublicSharesCards = cardRepository.getPublicSharesCards()
    }

}