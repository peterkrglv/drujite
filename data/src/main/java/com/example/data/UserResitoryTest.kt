package com.example.data

import com.example.domain.models.UserModel
import com.example.domain.use_cases.user.LoginResult
import com.example.domain.use_cases.user.SignupResult
import com.example.domain.repos.UserRepository
import com.example.domain.use_cases.user.LoginResponse
import com.example.domain.use_cases.user.SignupResponse

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

    override suspend fun getUserById(userId: Int): UserModel {
        return UserModel(
            id = 1,
            name = "Анастасия Чеботарь",
            phone = "+1234567890"
        )
    }
}