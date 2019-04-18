package com.shapeapp.shape.backendcalls

import com.google.gson.annotations.SerializedName

/**
 * Represents object received from network
 */
class User {

    @SerializedName("name")
    val name: String = ""

    @SerializedName("surname")
    val surname: String = ""

    @SerializedName("email")
    val email: String = ""

    @SerializedName("dateOfBirth")
    val birthDate: String = ""
}