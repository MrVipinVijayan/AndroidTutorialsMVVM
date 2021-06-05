package com.coderzheaven.tutorialprojects.repo

import com.coderzheaven.tutorialprojects.`interface`.UsersInterface
import com.coderzheaven.tutorialprojects.callback.UsersCallback
import com.coderzheaven.tutorialprojects.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersRepo(callback: UsersCallback?) : Callback<List<User>> {

    private val usersCallback: UsersCallback? = callback

    fun getAllUsers() {
        val request = RetrofitUtil.buildService(UsersInterface::class.java)
        val call = request.getUsers()
        call.enqueue(this)
    }

    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
        if (response.isSuccessful) {
            response.body()?.let { usersCallback?.onSuccess(it) }
            return
        }
        usersCallback?.onFailed()
    }

    override fun onFailure(call: Call<List<User>>, t: Throwable) {
        usersCallback?.onFailed()
    }
}