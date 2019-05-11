package com.shapeapp.shape.mockupsmakers

import java.text.SimpleDateFormat
import java.util.concurrent.ThreadLocalRandom

/**
 * Generates mock-ups related to date and time.
 */
object CalendarMockups {

    private const val MILLIS_FROM_EPOCH_IN_1995 = 800_000_000_000
    private const val MILLIS_FROM_EPOCH_2042 = 2_300_000_000_000

    fun generateRandomDateDDMMYYYY(): String {
        val randomMillisInRange: Long =
            ThreadLocalRandom.current().nextLong(MILLIS_FROM_EPOCH_IN_1995, MILLIS_FROM_EPOCH_2042)
        return getDateDDMMYYYY(randomMillisInRange)
    }

    fun getDateDDMMYYYY(millisSinceEpoch: Long): String {
        return SimpleDateFormat("dd-MM-yyyy").format(millisSinceEpoch)
    }

    fun generateRandomTimeHHMM(): String {
        val randomMillisInRange: Long =
            ThreadLocalRandom.current().nextLong(MILLIS_FROM_EPOCH_IN_1995, MILLIS_FROM_EPOCH_2042)
        return getTimeHHMMSS(randomMillisInRange)
    }

    fun getTimeHHMMSS(millisSinceEpoch: Long): String {
        return SimpleDateFormat("HH:mm:ss").format(millisSinceEpoch)
    }
}