package com.coderzheaven.tutorialprojects.`interface`

import com.coderzheaven.tutorialprojects.constants.Constants.Companion.USERS
import com.coderzheaven.tutorialprojects.models.User
import retrofit2.Call
import retrofit2.http.GET

interface UsersInterface {

    @GET(USERS)
    fun getUsers(): Call<List<User>>

}