package com.shapeapp.shape.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shapeapp.shape.mockupsmakers.TextMockups

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

    private val _officialCardsData = MutableLiveData<List<String>>()
    val officialCardsData: LiveData<List<String>>
        get() = _officialCardsData

    private val _newCardsData = MutableLiveData<List<String>>()
    val newCardsData: LiveData<List<String>>
        get() = _newCardsData

    private val _latestCardsData = MutableLiveData<List<String>>()
    val latestCardsData: LiveData<List<String>>
        get() = _latestCardsData

    init {
        _officialCardsData.value = TextMockups.animals
        _newCardsData.value = TextMockups.names
        _latestCardsData.value = TextMockups.cities
    }

}