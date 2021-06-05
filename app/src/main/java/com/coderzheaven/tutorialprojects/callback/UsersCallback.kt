package com.coderzheaven.tutorialprojects.callback

import com.coderzheaven.tutorialprojects.models.User
import com.coderzheaven.tutorialprojects.models.UserError

interface UsersCallback {

    fun onSuccess(users: List<User>)
    fun onFailed(userError: UserError)

}