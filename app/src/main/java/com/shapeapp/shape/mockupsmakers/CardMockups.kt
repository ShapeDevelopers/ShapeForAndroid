package com.shapeapp.shape.mockupsmakers

import com.shapeapp.shape.data.entities.Card
import java.util.*

/**
 * Generates [Card]s mock-ups
 */
object CardMockups {

    val animalCards by lazy { generateCardsWithSenderAndExtraText(TextMockups.animals) }
    val nameCards by lazy { generateCardsWithSenderAndExtraText(TextMockups.names) }
    val cityCards by lazy { generateCardsWithSenderAndExtraText(TextMockups.cities) }
    val countryCards by lazy { generateCardsWithSenderAndExtraText(TextMockups.countries) }

    private fun generateCardsWithSenderAndExtraText(senders: List<String>): List<Card> {
        val cards = ArrayList<Card>()

        for (sender in senders) {
            val newCard = Card().apply {
                senderNickname = sender
                extraText = "Hello from $sender!"
                imageUrl = DrawablesMockups.getRandomDrawableUriString()
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