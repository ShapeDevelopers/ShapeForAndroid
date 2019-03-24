package com.shapeapp.shape.mockupsmakers

import com.shapeapp.shape.data.Card

/**
 * Generates [Card]s mock-ups
 */
object CardMockups {

    val animalCards by lazy { generateCardsWithSender(TextMockups.animals) }
    val nameCards by lazy { generateCardsWithSender(TextMockups.names) }
    val cityCards by lazy { generateCardsWithSender(TextMockups.cities) }

    private fun generateCardsWithSender(senders: List<String>): List<Card> {
        val cards = ArrayList<Card>()

        for (sender in senders) {
            val newCard = Card().apply {
                senderNickname = sender
            }
            cards.add(newCard)
        }

        return cards
    }
}