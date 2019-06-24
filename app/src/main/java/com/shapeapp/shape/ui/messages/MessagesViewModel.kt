package com.shapeapp.shape.ui.messages

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shapeapp.shape.data.database.entities.Message
import com.shapeapp.shape.data.repositories.MessagesRepositoryObsolete

/**
 * [ViewModel] for [MessagesFragment]
 */
class MessagesViewModel(private val messagesRepository: MessagesRepositoryObsolete) : ViewModel() {

    //  TODO: finish

    var sentMessages: LiveData<List<Message>>
        private set
    var receivedMessages: LiveData<List<Message>>
        private set
    var latestMessages: LiveData<List<Message>>
        private set

    init {
        sentMessages = MessagesRepositoryObsolete.sentMessages
        receivedMessages = MessagesRepositoryObsolete.receivedMessages
        latestMessages = MessagesRepositoryObsolete.latestMessages
    }

}