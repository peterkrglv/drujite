package com.example.domain.repos

import com.example.domain.use_cases.LoginResponse
import com.example.domain.use_cases.SignupResponse

interface UserRepository {
    suspend fun login(phone: String, password: String): LoginResponse
    suspend fun signup(name: String, phone: String, password: String): SignupResponse
}