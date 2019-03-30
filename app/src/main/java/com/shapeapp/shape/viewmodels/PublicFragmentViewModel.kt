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

    var officialCardsData: LiveData<List<Card>>
        private set

    var newCardsData: LiveData<List<Card>>
        private set

    var latestCardsData: LiveData<List<Card>>
        private set


    init {
        officialCardsData = cardRepository.officialCards
        newCardsData = cardRepository.newCards
        latestCardsData = cardRepository.latestCards
    }

}