package com.shapeapp.shape.viewmodels

import android.net.Uri
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

    private val _backgroundImageUri = MutableLiveData<Uri>()
    val backgroundImageUri: LiveData<Uri>
        get() = _backgroundImageUri

    private val _senderNickname = MutableLiveData<String>()
    val senderNickname: LiveData<String>
        get() = _senderNickname

    private val _imageRemainingTime = MutableLiveData<String>()
    val imageRemainingTime: LiveData<String>
        get() = _imageRemainingTime

    private val _forCounterIndication = MutableLiveData<String>()
    val forCounterIndication: LiveData<String>
        get() = _forCounterIndication

    private val _againstCounterIndication = MutableLiveData<String>()
    val againstCounterIndication: LiveData<String>
        get() = _againstCounterIndication

    init {
        _extraText.value = card.extraText
        _backgroundImageUri.value = Uri.parse(card.imageUrl)
        _senderNickname.value = card.senderNickname
        _imageRemainingTime.value = card.remainingTimeInMin.toString()
        _forCounterIndication.value = card.votesForCounter.toString()
        _againstCounterIndication.value = card.votesAgainstCounter.toString()
    }

    fun forCounterClicked() {
        val oldValue: Int = _forCounterIndication.value?.toInt() ?: 0
        val newValue = oldValue + 1
        _forCounterIndication.value = newValue.toString()
    }

    fun againstCounterClicked() {
        val oldValue: Int = _againstCounterIndication.value?.toInt() ?: 0
        val newValue = oldValue + 1
        _againstCounterIndication.value = newValue.toString()
    }

}