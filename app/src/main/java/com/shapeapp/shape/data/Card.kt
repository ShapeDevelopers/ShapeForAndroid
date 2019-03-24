package com.shapeapp.shape.data

/**
 * Contains single card's data
 */
class Card {

    //  TODO: implement [Parcelable] interface in [Card] to pass it in [Bundle]

    var extraText = ""
    var imageUrl = ""
    var senderNickname = ""
    var remainingTimeInMin = 0
    var votesForCounter = 0
    var votesAgainstCounter = 0

}