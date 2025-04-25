package com.example.data.repos

import com.example.domain.models.CharacterModel
import com.example.domain.repos.CharacterRepository

class CharacterRepositoryImpl: CharacterRepository{
    override suspend fun addCharacter(userId: Int, name: String, clanId: Int): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun getUsersCharacters(userToken: String): List<CharacterModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterById(characterId: Int): CharacterModel? {
        TODO("Not yet implemented")
    }

    override suspend fun getCharactersBySessionId(sessionId: Int): List<CharacterModel> {
        TODO("Not yet implemented")
    }
}