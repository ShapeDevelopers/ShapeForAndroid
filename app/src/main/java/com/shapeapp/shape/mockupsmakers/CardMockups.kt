package com.shapeapp.shape.mockupsmakers

import com.shapeapp.shape.data.Card
import java.util.*

/**
 * Generates [Card]s mock-ups
 */
object CardMockups {

    val animalCards by lazy { generateCardsWithSenderAndExtraText(TextMockups.animals) }
    val nameCards by lazy { generateCardsWithSenderAndExtraText(TextMockups.names) }
    val cityCards by lazy { generateCardsWithSenderAndExtraText(TextMockups.cities) }

    private fun generateCardsWithSenderAndExtraText(senders: List<String>): List<Card> {
        //  TODO: generate full [Card]s

        val cards = ArrayList<Card>()

        for (sender in senders) {
            val newCard = Card().apply {
                senderNickname = sender
                extraText = "Hello from $sender!"
                remainingTimeInMin = generateRandomMinutes()
                votesForCounter = generateRandomVotesNumber()
                votesAgainstCounter = generateRandomVotesNumber()
            }
            cards.add(newCard)
        }

        return cards
    }

    private fun generateRandomMinutes() = Random().nextInt(60 * 26)

    private fun generateRandomVotesNumber() = Random().nextInt(23764)
}