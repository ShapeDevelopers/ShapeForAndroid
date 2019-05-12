package com.shapeapp.shape.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Contains single message's data
 */
class Message() : Parcelable {

    @SerializedName("senderNickname")
    var senderNickname = ""
    @SerializedName("receiverNickname")
    var receiverNickname = ""
    @SerializedName("textContent")
    var textContent = ""
    @SerializedName("dateStampDDMMYYYY")
    var dateStampDDMMYYYY = ""
    @SerializedName("dateStampHHMM")
    var dateStampHHMM = ""
    @SerializedName("dateStampFull")
    var dateStampFull = ""
        private set
        get() = "$dateStampDDMMYYYY $dateStampHHMM"
    @SerializedName("coordinates")
    var coordinates = ""
    @SerializedName("platform")
    var platform = ""
    @SerializedName("id")
    var id = ""

    constructor(parcel: Parcel) : this() {
        senderNickname = parcel.readString()
        receiverNickname = parcel.readString()
        textContent = parcel.readString()
        dateStampDDMMYYYY = parcel.readString()
        dateStampHHMM = parcel.readString()
        coordinates = parcel.readString()
        platform = parcel.readString()
        id = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(senderNickname)
        parcel.writeString(receiverNickname)
        parcel.writeString(textContent)
        parcel.writeString(dateStampDDMMYYYY)
        parcel.writeString(dateStampHHMM)
        parcel.writeString(coordinates)
        parcel.writeString(platform)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Message> {
        override fun createFromParcel(parcel: Parcel): Message {
            return Message(parcel)
        }

        override fun newArray(size: Int): Array<Message?> {
            return arrayOfNulls(size)
        }
    }

}