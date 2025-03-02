package com.example.drujite.data

import com.example.drujite.domain.LoginResult
import com.example.drujite.domain.SignupResult
import com.example.drujite.domain.UserRepository

class UserResitoryImpl: UserRepository {
    override suspend fun login(phone: String, password: String): LoginResult {
        return LoginResult.SUCCESS
    }
    override suspend fun signup(name: String, phone: String, password: String): SignupResult {
        return SignupResult.SUCCESS
    }
}