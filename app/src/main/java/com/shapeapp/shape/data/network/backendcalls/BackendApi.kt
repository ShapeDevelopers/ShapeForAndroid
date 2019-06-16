package com.shapeapp.shape.data.network.backendcalls

import com.shapeapp.shape.data.database.entities.Card
import retrofit2.Call
import retrofit2.http.GET

/**
 * Defines REST API for Retrofit
 */
interface BackendApi {

    @GET("cards")
    fun getCards(): Call<List<Card>>
}