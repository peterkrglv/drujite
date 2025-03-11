package com.example.data

import com.example.domain.use_cases.LoginResult
import com.example.domain.use_cases.SignupResult
import com.example.domain.repos.UserRepository
import com.example.domain.use_cases.LoginResponse
import com.example.domain.use_cases.SignupResponse

class UserResitoryTest : UserRepository {
    override suspend fun login(phone: String, password: String): LoginResponse {
        return LoginResponse(result = LoginResult.SUCCESS, id = 1)
    }

    override suspend fun signup(
        name: String,
        phone: String,
        password: String,
        gender: String
    ): SignupResponse {
        return SignupResponse(result = SignupResult.SUCCESS, id = 1)
    }
}