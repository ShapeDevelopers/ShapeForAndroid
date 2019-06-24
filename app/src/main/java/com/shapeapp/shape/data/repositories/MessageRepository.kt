package com.shapeapp.shape.data.repositories

import androidx.lifecycle.LiveData
import com.shapeapp.shape.data.database.entities.Message

/**
 * Defines repository for [Message]s
 */
interface MessageRepository {
    //  TODO: use DI with Kodein and "with singleton"
    //  TODO: use suspend functions

    fun getSentMessages(): LiveData<List<Message>>
    fun getReceivedMessages(): LiveData<List<Message>>
    fun getLatestMessages(): LiveData<List<Message>>

}