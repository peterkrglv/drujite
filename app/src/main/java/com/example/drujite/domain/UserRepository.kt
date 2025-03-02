package com.example.drujite.domain

interface UserRepository {
    suspend fun login(phone: String, password: String): LoginResult
    suspend fun signup(name: String, phone: String, password: String): SignupResult
}