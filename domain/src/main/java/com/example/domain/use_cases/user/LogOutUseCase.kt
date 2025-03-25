package com.example.domain.use_cases.user

import com.example.domain.repos.SharedPrefsRepository

class LogOutUseCase(private val repo: SharedPrefsRepository) {
    suspend fun execute() {
        repo.clearAll()
    }
}