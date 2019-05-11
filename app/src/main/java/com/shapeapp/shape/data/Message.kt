package com.shapeapp.shape.data

/**
 * Contains single message's data
 */
class Message {

    var senderNickname = ""
    var receiverNickname = ""
    var textContent = ""
    var dateStampDDMMYYYY = ""
    var dateStampHHMM = ""
    var dateStampFull = ""
        private set
        get() = "$dateStampDDMMYYYY $dateStampHHMM"
    var coordinates = ""
    var platform = ""
    var id = ""

}