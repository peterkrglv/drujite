package com.example.domain

data class SessionModel(
    val name: String,
    val description: String,
    val dates: String,
    val nOfPlayers: Int,
    val imageUrl: String?
)
