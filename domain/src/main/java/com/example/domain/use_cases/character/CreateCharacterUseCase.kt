package com.example.domain.use_cases.character

import com.example.domain.repos.CharacterRepository

class CreateCharacterUseCase(private val repo: CharacterRepository) {
    suspend fun execute(userId: Int, name: String, clanId: Int) = repo.addCharacter(userId, name, clanId)
}