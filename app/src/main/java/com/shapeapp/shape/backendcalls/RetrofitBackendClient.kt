package com.shapeapp.shape.backendcalls

import com.shapeapp.shape.backendcalls.RetrofitBackendClient.backendApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Constructs [backendApi] object to perform network calls
 */
object RetrofitBackendClient {

    private const val BASE_URL = "https://my-json-server.typicode.com/ShapeDevelopers/ShapeMockups/"

    val backendApi by lazy { initBackendApi() }


    private fun initBackendApi(): BackendApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(BackendApi::class.java)
    }

}