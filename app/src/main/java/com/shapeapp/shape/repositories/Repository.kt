package com.shapeapp.shape.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shapeapp.shape.data.Card
import com.shapeapp.shape.mockupsmakers.CardMockups

/**
 * Repository singleton
 *
 * "somedata" and "_somedata" are for proper encapsulation
 *
 * example:
 * "_somedata" is private MutableLiveData<...> and "somedata" is public LiveData<...>
 * with custom getter that returns "_somedata" as LiveData<...>
 * See: www.youtube.com/watch?v=5qlIPTDE274 (time: 2:46)
 */
object Repository {

    //  TODO: add model, remote data source and others
    //  see: https://developer.android.com/jetpack/docs/guide)

    private val _officialCardsData = MutableLiveData<List<Card>>()
    val officialCardsData: LiveData<List<Card>>
        get() = _officialCardsData

    private val _newCardsData = MutableLiveData<List<Card>>()
    val newCardsData: LiveData<List<Card>>
        get() = _newCardsData

    private val _latestCardsData = MutableLiveData<List<Card>>()
    val latestCardsData: LiveData<List<Card>>
        get() = _latestCardsData

    init {
        _officialCardsData.value = CardMockups.animalCards
        _newCardsData.value = CardMockups.nameCards
        _latestCardsData.value = CardMockups.cityCards
    }

}