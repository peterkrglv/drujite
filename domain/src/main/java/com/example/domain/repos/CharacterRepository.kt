package com.example.domain.repos

import com.example.domain.models.CharacterModel

interface CharacterRepository {
    suspend fun addCharacter(userId: Int, name: String, clanId: Int): Int
    suspend fun getCharactersByUserId(userId: Int): List<CharacterModel>
    suspend fun getCharacterById(characterId: Int): CharacterModel
}