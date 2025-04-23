package com.example.domain.models

data class SessionModel(
    val id: Int,
    val name: String,
    val description: String,
    val dates: String,
    val imageUrl: String?
)
