package com.shapeapp.shape.backendcalls

import com.shapeapp.shape.data.Card
import retrofit2.Call
import retrofit2.http.GET

/**
 * Defines REST API for Retrofit
 */
interface BackendApi {

    @GET("cards")
    fun getCards(): Call<List<Card>>
}