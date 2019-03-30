package com.shapeapp.shape.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shapeapp.shape.data.Card
import com.shapeapp.shape.fragments.ReceivedImageFragment

/**
 * [ViewModel] for [ReceivedImageFragment]
 */
class ReceivedImageFragmentViewModel(card: Card) : ViewModel() {

    private val _extraText = MutableLiveData<String>()
    val extraText: LiveData<String>
        get() = _extraText

    private val _backgroundImageUri = MutableLiveData<String>()
    val backgroundImageUri: LiveData<String>
        get() = _backgroundImageUri

    private val _senderNickname = MutableLiveData<String>()
    val senderNickname: LiveData<String>
        get() = _senderNickname

    private val _imageRemainingTime = MutableLiveData<Int>()
    val imageRemainingTime: LiveData<Int>
        get() = _imageRemainingTime

    private val _forCounterIndication = MutableLiveData<Int>()
    val forCounterIndication: LiveData<Int>
        get() = _forCounterIndication

    private val _againstCounterIndication = MutableLiveData<Int>()
    val againstCounterIndication: LiveData<Int>
        get() = _againstCounterIndication

    init {
        _extraText.value = card.extraText
        _backgroundImageUri.value = card.imageUrl
        _senderNickname.value = card.senderNickname
        _imageRemainingTime.value = card.remainingTimeInMin
        _forCounterIndication.value = card.votesForCounter
        _againstCounterIndication.value = card.votesAgainstCounter
    }

}