package com.coderzheaven.tutorialprojects.repo.user_repo

import com.coderzheaven.tutorialprojects.`interface`.UsersInterface

class UserRepoImplementation(private val usersInterface: UsersInterface) : UserRepoHelper {

    override suspend fun getAllUsers() = usersInterface.getUsers()

}