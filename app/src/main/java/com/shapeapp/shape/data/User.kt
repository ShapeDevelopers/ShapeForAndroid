package com.shapeapp.shape.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Contains single user's data
 */
class User() : Parcelable {

    var nickname = ""
    var avatarUri = ""
    var name = ""
    var surname = ""
    var sex = ""
    var email = ""
    var birthDate = ""
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