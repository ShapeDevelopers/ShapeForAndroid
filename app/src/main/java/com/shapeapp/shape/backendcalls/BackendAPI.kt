package com.shapeapp.shape.backendcalls

import retrofit2.Call
import retrofit2.http.GET

/**
 * Defines REST API for Retrofit
 */
interface BackendAPI {

    @GET("user")
    fun getUser(): Call<User>
}