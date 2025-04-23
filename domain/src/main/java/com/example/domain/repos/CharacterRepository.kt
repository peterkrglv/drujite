package com.example.domain.repos

import com.example.domain.models.CharacterModel

interface CharacterRepository {
    suspend fun addCharacter(userId: Int, name: String, clanId: Int): Int
    suspend fun getUsersCharacters(userToken: String): List<CharacterModel>
    suspend fun getCharacterById(characterId: Int): CharacterModel
    suspend fun getCharactersBySessionId(sessionId: Int): List<CharacterModel>
}