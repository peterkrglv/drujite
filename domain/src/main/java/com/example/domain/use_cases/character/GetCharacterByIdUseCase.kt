package com.example.domain.use_cases.character

import com.example.domain.repos.CharacterRepository

class GetCharacterByIdUseCase(private val repo: CharacterRepository) {
    suspend fun execute(characterId: Int) = repo.getCharacterById(characterId)
}