package com.shapeapp.shape.mockupsmakers

import com.shapeapp.shape.data.User
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * Generates [User]s mock-ups
 */
object UsersMockups {

    val randomUser
        get() = generateRandomUser()


    private fun generateRandomUser(): User {
        val nickname = getRandomNick()
        val avatarUri = DrawablesMockups.getRandomDrawableUriString()
        val name = getRandomName()
        val surname = "${getRandomName()}ovsky"
        val sex = getRandomSex()
        val email = "$nickname@gmail.com"
        val birthDate = getRandomDate()
        val radarRadius = getRandomRadarRadius()

        return User().also {
            it.nickname = nickname
            it.avatarUri = avatarUri
            it.name = name
            it.surname = surname
            it.sex = sex
            it.email = email
            it.birthDate = birthDate
            it.radarRadius = radarRadius
        }
    }

    private fun getRandomNick(): String {
        val nameSegment = TextMockups.randomAnimal
        val numeralSegment = getRandomSmallNumber().toString()
        return "${nameSegment}_$numeralSegment"
    }

    private fun getRandomSmallNumber(): Int {
        return Random.nextInt(12..87)
    }

    private fun getRandomName(): String {
        return TextMockups.randomName
    }

    private fun getRandomSex(): String {
        val randomNumber = Random.nextInt()
        return when (isEven(randomNumber)) {
            true -> "male"
            false -> "female"
        }
    }

    private fun isEven(number: Int): Boolean {
        return (number % 2 == 0)
    }

    private fun getRandomDate(): String {
        return CalendarMockups.generateRandomDateDDMMYYYY()
    }

    private fun getRandomRadarRadius(): String {
        return Random.nextInt(0..239).toString()
    }

}