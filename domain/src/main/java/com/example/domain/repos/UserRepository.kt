package com.example.domain.repos

import com.example.domain.models.UserModel
import com.example.domain.use_cases.user.LoginResponse
import com.example.domain.use_cases.user.SignupResponse

interface UserRepository {
    suspend fun login(phone: String, password: String): LoginResponse
    suspend fun signup(name: String, phone: String, password: String, gender: String): SignupResponse
    suspend fun getUserById(userId: Int): UserModel
}