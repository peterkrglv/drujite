package com.example.data

import com.example.domain.repos.CharacterRepository

class CharacterReposirotyTest: CharacterRepository {
    override suspend fun addCharacter(userId: Int, name: String, clanId: Int): Boolean {
        return true
    }
}