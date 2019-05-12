package com.shapeapp.shape.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shapeapp.shape.data.Message
import com.shapeapp.shape.fragments.MessagesFragment
import com.shapeapp.shape.repositories.CardsRepository
import com.shapeapp.shape.repositories.MessagesRepository

/**
 * [ViewModel] for [MessagesFragment]
 */
class MessagesFragmentViewModel(private val messageRepository: CardsRepository) : ViewModel() {

    //  TODO: finish

    var sentMessages: LiveData<List<Message>>
        private set
    var receivedMessages: LiveData<List<Message>>
        private set
    var latestMessages: LiveData<List<Message>>
        private set

    init {
        sentMessages = MessagesRepository.sentMessages
        receivedMessages = MessagesRepository.receivedMessages
        latestMessages = MessagesRepository.latestMessages
    }

}