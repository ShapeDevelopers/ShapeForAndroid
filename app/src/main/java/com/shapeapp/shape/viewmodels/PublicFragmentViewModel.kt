package com.shapeapp.shape.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shapeapp.shape.fragments.PublicFragment
import com.shapeapp.shape.fragments.ReceivedImageFragment
import com.shapeapp.shape.repositories.Repository

/**
 * [ViewModel] for [PublicFragment] and for data sharing with [ReceivedImageFragment]
 */
class PublicFragmentViewModel : ViewModel() {

    //  TODO: clean
    //  TODO: implement full data loading from repository

    private val repository: Repository = Repository

    var officialCardsData: LiveData<List<String>>
        private set

    var newCardsData: LiveData<List<String>>
        private set

    var latestCardsData: LiveData<List<String>>
        private set

    //  TODO: consider other way to pass data to [ReceivedImageFragment] (?)
    private val _selectedCardText = MutableLiveData<String>()
    val selectedCardText: LiveData<String>
        get() = _selectedCardText

    init {
        officialCardsData = repository.officialCardsData
        newCardsData = repository.newCardsData
        latestCardsData = repository.latestCardsData

        _selectedCardText.value = ""
    }


    fun selectCardText(selectedText: String) {
        _selectedCardText.value = selectedText
    }

}