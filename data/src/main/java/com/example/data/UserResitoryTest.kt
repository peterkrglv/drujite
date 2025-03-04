package com.example.data

import com.example.domain.use_cases.LoginResult
import com.example.domain.use_cases.SignupResult
import com.example.domain.repos.UserRepository

class UserResitoryTest : UserRepository {
    override suspend fun login(phone: String, password: String): LoginResult {
        return LoginResult.SUCCESS
    }

    override suspend fun signup(
        name: String,
        phone: String,
        password: String
    ): SignupResult {
        return SignupResult.SUCCESS
    }
}