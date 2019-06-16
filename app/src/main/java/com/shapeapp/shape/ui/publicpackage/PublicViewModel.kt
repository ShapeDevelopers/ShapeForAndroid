package com.shapeapp.shape.ui.publicpackage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shapeapp.shape.data.entities.Card
import com.shapeapp.shape.data.repositories.CardsRepository

/**
 * [ViewModel] for [PublicFragment]
 */
class PublicViewModel(private val cardRepository: CardsRepository) : ViewModel() {

    //  TODO: implement full data loading from repository

    var officialCards: LiveData<List<Card>>
        private set

    var newCards: LiveData<List<Card>>
        private set

    var latestCards: LiveData<List<Card>>
        private set


    init {
        officialCards = cardRepository.officialCards
        newCards = cardRepository.newCards
        latestCards = cardRepository.latestCards
    }

}