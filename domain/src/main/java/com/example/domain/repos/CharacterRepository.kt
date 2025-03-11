package com.example.domain.repos

import com.example.domain.models.CharacterModel

interface CharacterRepository {
    suspend fun addCharacter(userId: Int, name: String, clanId: Int): Int
    suspend fun getCharacterByUserId(userId: Int): List<CharacterModel>
}