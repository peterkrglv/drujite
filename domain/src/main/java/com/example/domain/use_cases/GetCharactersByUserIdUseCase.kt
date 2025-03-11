package com.example.domain.use_cases

import com.example.domain.repos.CharacterRepository

class GetCharactersByUserIdUseCase(private val repo: CharacterRepository) {
    suspend fun execute(userId: Int) = repo.getCharacterByUserId(userId)
}