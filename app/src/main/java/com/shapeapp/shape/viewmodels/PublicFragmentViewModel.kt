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

    init {
        officialCardsData.value = TextMockups.animals
    }


    fun getOfficialCardsData(): LiveData<List<String>> {
        return officialCardsData
    }

}