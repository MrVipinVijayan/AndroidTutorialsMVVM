package com.coderzheaven.tutorialprojects.repo.user_repo

import com.coderzheaven.tutorialprojects.models.User

interface UserRepoHelper {

    suspend fun getAllUsers(): List<User>

}