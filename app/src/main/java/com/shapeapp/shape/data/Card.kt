package com.shapeapp.shape.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Contains single card's data
 */
class Card() : Parcelable {

    var extraText = ""
    var imageUrl = ""
    var senderNickname = ""
    var remainingTimeInMin = 0
    var votesForCounter = 0
    var votesAgainstCounter = 0

    constructor(parcel: Parcel) : this() {
        extraText = parcel.readString()
        imageUrl = parcel.readString()
        senderNickname = parcel.readString()
        remainingTimeInMin = parcel.readInt()
        votesForCounter = parcel.readInt()
        votesAgainstCounter = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(extraText)
        parcel.writeString(imageUrl)
        parcel.writeString(senderNickname)
        parcel.writeInt(remainingTimeInMin)
        parcel.writeInt(votesForCounter)
        parcel.writeInt(votesAgainstCounter)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Card> {
        override fun createFromParcel(parcel: Parcel): Card {
            return Card(parcel)
        }

        override fun newArray(size: Int): Array<Card?> {
            return arrayOfNulls(size)
        }
    }

}