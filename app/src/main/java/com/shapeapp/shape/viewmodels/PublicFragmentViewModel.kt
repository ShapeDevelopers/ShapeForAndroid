package com.shapeapp.shape.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shapeapp.shape.fragments.PublicFragment
import com.shapeapp.shape.fragments.ReceivedImageFragment
import com.shapeapp.shape.mockupsmakers.TextMockups

/**
 * [ViewModel] for [PublicFragment] and for data sharing with [ReceivedImageFragment]
 *
 * "somedata" and "_somedata" are for proper encapsulation
 */
class PublicFragmentViewModel : ViewModel() {

    //  TODO: clean
    //  TODO: implement loading data from repository (now there is no real repo, fake data is loaded)

    private val _officialCardsData = MutableLiveData<List<String>>()
    val officialCardsData: LiveData<List<String>>
        get() = _officialCardsData

    private val _newCardsData = MutableLiveData<List<String>>()
    val newCardsData: LiveData<List<String>>
        get() = _newCardsData

    private val _latestCardsData = MutableLiveData<List<String>>()
    val latestCardsData: LiveData<List<String>>
        get() = _latestCardsData


    private val _selectedCardText = MutableLiveData<String>()
    val selectedCardText: LiveData<String>
        get() = _selectedCardText

    init {
        _officialCardsData.value = TextMockups.animals
        _newCardsData.value = TextMockups.cities
        _latestCardsData.value = TextMockups.names

        _selectedCardText.value = ""
    }


    fun selectCardText(selectedText: String) {
        _selectedCardText.value = selectedText
    }

}