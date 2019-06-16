package com.shapeapp.shape.ui.dialogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shapeapp.shape.data.database.entities.Card
import com.shapeapp.shape.data.repositories.CardsRepository

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