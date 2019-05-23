package com.shapeapp.shape.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Contains single user's data
 */
class User() : Parcelable {

    @SerializedName("nickname")
    var nickname = ""

    @SerializedName("avatarUri")
    var avatarUri = ""

    @SerializedName("name")
    var name = ""

    @SerializedName("surname")
    var surname = ""

    @SerializedName("sex")
    var sex = ""

    @SerializedName("email")
    var email = ""

    @SerializedName("birthDate")
    var birthDate = ""

    @SerializedName("radarRadius")
    var radarRadius = ""

    constructor(parcel: Parcel) : this() {
        nickname = parcel.readString()
        avatarUri = parcel.readString()
        name = parcel.readString()
        surname = parcel.readString()
        sex = parcel.readString()
        email = parcel.readString()
        birthDate = parcel.readString()
        radarRadius = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nickname)
        parcel.writeString(avatarUri)
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(sex)
        parcel.writeString(email)
        parcel.writeString(birthDate)
        parcel.writeString(radarRadius)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}