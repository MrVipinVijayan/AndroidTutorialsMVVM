package com.coderzheaven.tutorialprojects.callback

import com.coderzheaven.tutorialprojects.models.User

interface UsersCallback {

    fun onSuccess(users: List<User>)
    fun onFailed()

}