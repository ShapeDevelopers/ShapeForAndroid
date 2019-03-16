package com.shapeapp.shape.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shapeapp.shape.mockupsmakers.TextMockups

/**
 * Repository singleton
 *
 * "somedata" and "_somedata" are for proper encapsulation
 */
object Repository {

    //  TODO: add model, remote data source and others
    //  see: https://developer.android.com/jetpack/docs/guide)

    private val _officialCardsData = MutableLiveData<List<String>>()
    val officialCardsData: LiveData<List<String>>
        get() = _officialCardsData

    private val _newCardsData = MutableLiveData<List<String>>()
    val newCardsData: LiveData<List<String>>
        get() = _newCardsData

    init {
        _officialCardsData.value = TextMockups.animals
        _newCardsData.value = TextMockups.names
    }

}