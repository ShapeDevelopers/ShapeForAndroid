package com.shapeapp.shape.backendcalls

import retrofit2.Call

interface BackendAPI {

    fun getUser(): Call<User>
}