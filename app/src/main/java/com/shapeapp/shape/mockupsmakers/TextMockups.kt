package com.shapeapp.shape.mockupsmakers

import java.util.*

/**
 * Helper class that provides some mock-up text data
 * e.g.: [List<String>] with animal species
 */
object TextMockups {

    val animals = listOf(
        "akita",
        "bat",
        "cat",
        "dog",
        "eagle",
        "fennec",
        "goat",
        "hamster",
        "iguana",
        "jaguar",
        "kangaroo",
        "lion",
        "mole",
        "newfoundland",
        "ocelot",
        "parrot",
        "quokka",
        "rabbit",
        "seal",
        "turkey",
        "uakari",
        "vulture",
        "wasp",
        "x-ray tetra",
        "yak",
        "zebra"
    )

    val cities = listOf(
        "Oslo",
        "Wroclaw",
        "Berlin",
        "London",
        "Paris",
        "Helsinki",
        "Stockholm",
        "Washington",
        "Rio de Janeiro",
        "Tokio",
        "Shenzhen",
        "Delhi",
        "Seul",
        "Los Angeles",
        "Buenos Aires"
    )

    val names = listOf(
        "Lucas",
        "Oliver",
        "George",
        "Emma",
        "Evelyn",
        "Mia",
        "Charlotte",
        "Ava",
        "Noah",
        "Liam",
        "Benjamin",
        "Mason",
        "Abigail",
        "Elijah",
        "Amelia",
        "Sophia",
        "Logan",
        "James"
    )

    val countries = listOf(
        "Germany",
        "Norway",
        "France",
        "Poland",
        "Sweden",
        "USA",
        "Italy",
        "China",
        "Japan",
        "Finland",
        "Hungary",
        "Austria",
        "Iceland",
        "Portugal",
        "Denmark",
        "Australia",
        "Egypt",
        "Brazil",
        "Canada",
        "Estonia"
    )

    val fruits = listOf(
        "apple",
        "pomegranate",
        "lemon",
        "raspberry",
        "apricot",
        "banana",
        "lychee",
        "nectarine",
        "orange",
        "fig",
        "lime",
        "quince",
        "mandarin",
        "watermelon",
        "boysenberry",
        "cherry",
        "mango",
        "papaya",
        "coconut",
        "strawberry",
        "plum",
        "blueberry",
        "kiwifruit",
        "grape"
    )

    fun generateRandomText(): String {
        val sourceWords = animals
        val sourceWordsNumber = sourceWords.size
        val newTextWordsNumber = Random().nextInt(sourceWordsNumber)
        var newText = "I like "
        for (index in 0 until newTextWordsNumber) {
            val randomNumber = Random().nextInt(sourceWordsNumber)
            newText += " ${sourceWords[randomNumber]}"
        }
        return newText
    }

}