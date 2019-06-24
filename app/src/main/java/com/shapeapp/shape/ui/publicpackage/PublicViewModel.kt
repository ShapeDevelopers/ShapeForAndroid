package com.shapeapp.shape.ui.publicpackage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shapeapp.shape.data.database.entities.Card
import com.shapeapp.shape.data.repositories.CardRepository

/**
 * [ViewModel] for [PublicFragment]
 */
class PublicViewModel(private val cardRepository: CardRepository) : ViewModel() {

    //  TODO: implement full data loading from repository
    //  TODO: implement Dependency Injection with Kodein

    var officialCards: LiveData<List<Card>>
        private set

    var newCards: LiveData<List<Card>>
        private set

    var latestCards: LiveData<List<Card>>
        private set


    init {
        officialCards = cardRepository.getOfficialCards()
        newCards = cardRepository.getNewCards()
        latestCards = cardRepository.getLatestCards()
    }

}