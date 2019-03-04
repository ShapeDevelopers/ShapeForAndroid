package com.shapeapp.shape.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shapeapp.shape.fragments.PublicFragment
import com.shapeapp.shape.mockupsmakers.TextMockups

/**
 * [ViewModel] for [PublicFragment]
 */
class PublicFragmentViewModel : ViewModel() {

    //  TODO: clean
    //  TODO: there is no real repo (fake data is loaded)

    private val officialCardsData = MutableLiveData<List<String>>()
    private val newCardsData = MutableLiveData<List<String>>()
    private val latestCardsData = MutableLiveData<List<String>>()

    init {
        officialCardsData.value = TextMockups.animals
        newCardsData.value = TextMockups.cities
        latestCardsData.value = TextMockups.names
    }


    fun getOfficialCardsData(): LiveData<List<String>> {
        return officialCardsData
    }

    fun getNewCardsData(): LiveData<List<String>> {
        return newCardsData
    }

    fun getLatestCardsData(): LiveData<List<String>> {
        return latestCardsData
    }

}