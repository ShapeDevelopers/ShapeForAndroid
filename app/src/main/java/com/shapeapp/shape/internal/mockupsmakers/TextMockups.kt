package com.shapeapp.shape.internal.mockupsmakers

import kotlin.random.Random
import kotlin.random.nextInt


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
    val randomAnimal
        get() = getRandomElement(animals)

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
    val randomCity
        get() = getRandomElement(cities)

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
    val randomName
        get() = getRandomElement(names)

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
    val randomCountry
        get() = getRandomElement(countries)

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
    val randomFruit
        get() = getRandomElement(fruits)


    private fun getRandomElement(list: List<String>): String {
        val availableRange = 0 until list.size
        val randomPosition = Random.nextInt(availableRange)
        return list[randomPosition]
    }

    fun generateRandomText(): String {
        val sourceWords = animals
        val sourceWordsNumber = sourceWords.size
        val newTextWordsNumber = Random.nextInt(sourceWordsNumber)
        var newText = "I like "
        for (index in 0 until newTextWordsNumber) {
            val randomNumber = Random.nextInt(sourceWordsNumber)
            newText += " ${sourceWords[randomNumber]}"
        }
        return newText
    }

}