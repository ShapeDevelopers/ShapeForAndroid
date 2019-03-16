package com.shapeapp.shape.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shapeapp.shape.fragments.PublicFragment
import com.shapeapp.shape.repositories.Repository

/**
 * [ViewModel] for [PublicFragment]
 */
class PublicFragmentViewModel : ViewModel() {

    //  TODO: implement full data loading from repository

    private val repository: Repository = Repository

    var officialCardsData: LiveData<List<String>>
        private set

    var newCardsData: LiveData<List<String>>
        private set

    var latestCardsData: LiveData<List<String>>
        private set


    init {
        officialCardsData = repository.officialCardsData
        newCardsData = repository.newCardsData
        latestCardsData = repository.latestCardsData
    }

}