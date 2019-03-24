package com.shapeapp.shape.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shapeapp.shape.data.Card
import com.shapeapp.shape.fragments.PublicFragment
import com.shapeapp.shape.repositories.Repository

/**
 * [ViewModel] for [PublicFragment]
 */
class PublicFragmentViewModel : ViewModel() {

    //  TODO: implement full data loading from repository

    private val repository: Repository = Repository

    var officialCardsData: LiveData<List<Card>>
        private set

    var newCardsData: LiveData<List<Card>>
        private set

    var latestCardsData: LiveData<List<Card>>
        private set


    init {
        officialCardsData = repository.officialCards
        newCardsData = repository.newCards
        latestCardsData = repository.latestCards
    }

}