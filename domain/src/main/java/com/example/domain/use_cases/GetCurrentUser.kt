package com.example.domain.use_cases

import com.example.domain.models.UserModel
import com.example.domain.repos.SharedPrefsRepository
import com.example.domain.repos.UserRepository

class GetCurrentUser(private val sharedPrefs: SharedPrefsRepository, val repo: UserRepository) {
    suspend fun execute(): UserModel {
        val userId = sharedPrefs.getUserId()
        return repo.getUserById(userId)
    }
}