package com.shapeapp.shape.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shapeapp.shape.data.Card
import com.shapeapp.shape.fragments.PublicFragment
import com.shapeapp.shape.repositories.Repository

/**
 * [ViewModel] for [PublicFragment]
 */
class PublicFragmentViewModel(private val cardRepository: Repository) : ViewModel() {

    //  TODO: implement full data loading from repository

    var officialCards: LiveData<List<Card>>
        private set

    var newCards: LiveData<List<Card>>
        private set

    var latestCards: LiveData<List<Card>>
        private set

    var publicSharesCards: LiveData<List<Card>>
        private set


    init {
        officialCards = cardRepository.officialCards
        newCards = cardRepository.newCards
        latestCards = cardRepository.latestCards
        publicSharesCards = cardRepository.publicSharesCards
    }

}