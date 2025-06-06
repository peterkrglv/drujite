package com.example.data.requests

data class LoginRequest(
    val phone: String,
    val password: String
)

data class SignupRequest(
    val username: String,
    val phone: String,
    val password: String,
    val gender: String
)