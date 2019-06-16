package com.shapeapp.shape.mockupsmakers

import com.shapeapp.shape.data.entities.Message

/**
 * Generates [Message]s mock-ups
 */
object MessageMockups {

    val randomFullMessages
        get() = generateFullMessages()

    private fun generateFullMessages(senders: List<String> = TextMockups.names): List<Message> {
        val messages = ArrayList<Message>()
        for (sender in senders) {
            val newMessage = Message().apply {
                senderNickname = sender
                receiverNickname = "you"
                textContent = TextMockups.generateRandomText()
                dateStampDDMMYYYY = CalendarMockups.generateRandomDateDDMMYYYY()
                dateStampHHMM = CalendarMockups.generateRandomTimeHHMM()
                coordinates = "62.472229,6.149482"
                platform = "Android"
                id = "1A23"
            }
            messages.add(newMessage)
        }
        return messages
    }
}