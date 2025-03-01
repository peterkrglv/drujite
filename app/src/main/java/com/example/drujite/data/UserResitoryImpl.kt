package com.example.drujite.data

import com.example.drujite.domain.UserRepository

class UserResitoryImpl: UserRepository {
    override suspend fun login(phone: String, password: String): Boolean {
        return true
    }
}