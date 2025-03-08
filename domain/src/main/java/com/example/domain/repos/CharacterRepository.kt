package com.example.domain.repos

interface CharacterRepository {
    suspend fun addCharacter(userId: Int, name: String, clanId: Int): Boolean
}