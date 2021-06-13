package com.coderzheaven.tutorialprojects.repo

import com.coderzheaven.tutorialprojects.`interface`.UsersInterface
import com.coderzheaven.tutorialprojects.constants.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUtil {

    companion object {

        private val client = OkHttpClient.Builder().build()

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        fun <T> buildService(service: Class<T>): T {
            return retrofit.create(service)
        }

        fun usersInterface(): UsersInterface {
            return buildService(UsersInterface::class.java)
        }

    }
}