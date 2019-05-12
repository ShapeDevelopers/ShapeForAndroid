package com.shapeapp.shape.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shapeapp.shape.data.Message
import com.shapeapp.shape.fragments.MessagesFragment
import com.shapeapp.shape.mockupsmakers.MessageMockups
import com.shapeapp.shape.repositories.CardsRepository

/**
 * [ViewModel] for [MessagesFragment]
 */
class MessagesFragmentViewModel(private val messageRepository: CardsRepository) : ViewModel() {

    //  TODO: implement full data loading from repository

    //  TODO: finish

    var sentMessages: LiveData<List<Message>>
        private set
    var receivedMessages: LiveData<List<Message>>
        private set
    var latestMessages: LiveData<List<Message>>
        private set

    init {
        sentMessages = MutableLiveData<List<Message>>().apply { value = MessageMockups.randomFullMessages }
        receivedMessages = MutableLiveData<List<Message>>().apply { value = MessageMockups.randomFullMessages }
        latestMessages = MutableLiveData<List<Message>>().apply { value = MessageMockups.randomFullMessages }
    }

}