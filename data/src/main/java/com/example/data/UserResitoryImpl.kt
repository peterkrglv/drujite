package com.example.data

import com.example.domain.LoginResult
import com.example.domain.SignupResult
import com.example.domain.UserRepository

class UserResitoryImpl : UserRepository {
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