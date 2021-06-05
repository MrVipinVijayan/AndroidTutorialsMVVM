package com.coderzheaven.tutorialprojects.repo

import com.coderzheaven.tutorialprojects.`interface`.UsersInterface
import com.coderzheaven.tutorialprojects.callback.UsersCallback
import com.coderzheaven.tutorialprojects.models.User
import com.coderzheaven.tutorialprojects.models.UserError
import com.coderzheaven.tutorialprojects.constants.Constants.Companion.UNKNOWN_ERROR
import com.coderzheaven.tutorialprojects.constants.Constants.Companion.UNKNOWN_ERROR_CODE
import com.coderzheaven.tutorialprojects.constants.Constants.Companion.USER_LOAD_FAILURE
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
        val userError = UserError(UNKNOWN_ERROR_CODE, UNKNOWN_ERROR)
        usersCallback?.onFailed(userError)
    }

    override fun onFailure(call: Call<List<User>>, t: Throwable) {
        val userError = UserError(USER_LOAD_FAILURE, t.message.toString())
        usersCallback?.onFailed(userError)
    }
}