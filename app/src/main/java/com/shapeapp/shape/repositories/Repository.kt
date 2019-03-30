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
    //  see: https://developer.android.com/jetpack/docs/guide

    private val _officialCards = MutableLiveData<List<Card>>()
    val officialCards: LiveData<List<Card>>
        get() = _officialCards

    private val _newCards = MutableLiveData<List<Card>>()
    val newCards: LiveData<List<Card>>
        get() = _newCards

    private val _latestCards = MutableLiveData<List<Card>>()
    val latestCards: LiveData<List<Card>>
        get() = _latestCards

    init {
        _officialCards.value = CardMockups.animalCards
        _newCards.value = CardMockups.nameCards
        _latestCards.value = CardMockups.cityCards
    }

}