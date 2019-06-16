package com.shapeapp.shape.data.database.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Contains single card's data
 */
class Card() : Parcelable {

    @SerializedName("extraText")
    var extraText = ""
    @SerializedName("imageUrl")
    var imageUrl = ""
    @SerializedName("senderNickname")
    var senderNickname = ""
    @SerializedName("remainingTimeInMin")
    var remainingTimeInMin = 0
    @SerializedName("votesForCounter")
    var votesForCounter = 0
    @SerializedName("votesAgainstCounter")
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

    override fun toString(): String {
        return "Card(\n" +
                "extraText='$extraText',\n" +
                "imageUrl='$imageUrl',\n" +
                "senderNickname= '$senderNickname',\n" +
                "remainingTimeInMin='$remainingTimeInMin',\n" +
                "votesForCounter='$votesForCounter',\n" +
                "votesAgainstCounter='$votesAgainstCounter'\n" +
                ")"
    }
}