package com.shapeapp.shape.dialogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shapeapp.shape.data.entities.Card
import com.shapeapp.shape.repositories.CardsRepository

/**
 * [ViewModel] for [MyPublicSharesBSD]
 */
class MyPublicSharesBSDViewModel(private val cardRepository: CardsRepository) : ViewModel() {

    var myPublicSharesCards: LiveData<List<Card>>
        private set

    init {
        myPublicSharesCards = cardRepository.publicSharesCards
    }

}