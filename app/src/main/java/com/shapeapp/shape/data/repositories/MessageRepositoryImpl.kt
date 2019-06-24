package com.shapeapp.shape.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shapeapp.shape.data.database.entities.Message
import com.shapeapp.shape.internal.mockupsmakers.MessageMockups

/**
 * Implementation of [MessageRepository]
 */
class MessageRepositoryImpl : MessageRepository {

    //  TODO: add model, loading data from remote, ...

    private val _sentMessages = MutableLiveData<List<Message>>()
    private val _receivedMessages = MutableLiveData<List<Message>>()
    private val _latestMessages = MutableLiveData<List<Message>>()


    init {
        _sentMessages.value = MessageMockups.randomFullMessages
        _receivedMessages.value = MessageMockups.randomFullMessages
        _latestMessages.value = MessageMockups.randomFullMessages
    }


    override fun getSentMessages(): LiveData<List<Message>> {
        return _sentMessages
    }

    override fun getReceivedMessages(): LiveData<List<Message>> {
        return _receivedMessages
    }

    override fun getLatestMessages(): LiveData<List<Message>> {
        return _latestMessages
    }
}