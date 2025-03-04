package com.example.domain.repos

import com.example.domain.use_cases.LoginResult
import com.example.domain.use_cases.SignupResult

interface UserRepository {
    suspend fun login(phone: String, password: String): LoginResult
    suspend fun signup(name: String, phone: String, password: String): SignupResult
}