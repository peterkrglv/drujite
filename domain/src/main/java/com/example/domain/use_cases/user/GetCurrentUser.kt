package com.example.domain.use_cases.user

import com.example.domain.models.UserModel
import com.example.domain.repos.SharedPrefsRepository
import com.example.domain.repos.UserRepository

class GetCurrentUser(private val sharedPrefs: SharedPrefsRepository, val repo: UserRepository) {
    suspend fun execute(): UserModel? {
        val userToken = sharedPrefs.getUserToken()
        return userToken?.let { repo.getUserByToken(it) }
    }
}