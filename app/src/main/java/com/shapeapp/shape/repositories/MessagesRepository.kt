package com.shapeapp.shape.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shapeapp.shape.data.entities.Message
import com.shapeapp.shape.mockupsmakers.MessageMockups

/**
 * Repository with messages - singleton
 *
 * "somedata" and "_somedata" are for proper encapsulation
 * example:
 * "_somedata" is private MutableLiveData<...> and "somedata" is public LiveData<...>
 * with custom getter that returns "_somedata" as LiveData<...>
 * See: www.youtube.com/watch?v=5qlIPTDE274 (time: 2:46)
 */
@Suppress("ObjectPropertyName")
object MessagesRepository {

    //  TODO: add model, loading data from remote, ...

    private val _sentMessages = MutableLiveData<List<Message>>()
    val sentMessages: LiveData<List<Message>>
        get() = _sentMessages

    private val _receivedMessages = MutableLiveData<List<Message>>()
    val receivedMessages: LiveData<List<Message>>
        get() = _receivedMessages

    private val _latestMessages = MutableLiveData<List<Message>>()
    val latestMessages: LiveData<List<Message>>
        get() = _latestMessages

    init {
        _sentMessages.value = MessageMockups.randomFullMessages
        _receivedMessages.value = MessageMockups.randomFullMessages
        _latestMessages.value = MessageMockups.randomFullMessages
    }

}