package com.coderzheaven.tutorialprojects.`interface`

import com.coderzheaven.tutorialprojects.models.User
import retrofit2.Call
import retrofit2.http.GET

interface UsersInterface {

    @GET("users")
    fun getUsers(): Call<List<User>>

}