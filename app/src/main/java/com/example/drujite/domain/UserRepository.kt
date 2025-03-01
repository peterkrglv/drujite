package com.example.drujite.domain

interface UserRepository {
    suspend fun login(phone: String, password: String): Boolean
}