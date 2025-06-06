package com.example.data.requests

data class AddUsersCharacterToSession (
    val sessionId: Int,
    val characterId: Int
)