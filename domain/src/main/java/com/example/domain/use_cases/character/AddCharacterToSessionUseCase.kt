package com.example.domain.use_cases.character

import com.example.domain.repos.SessionRepository

class AddCharacterToSessionUseCase(private val repo: SessionRepository) {
    suspend fun execute(sessionId: Int, characterId: Int) = repo.addCharacterToSession(sessionId, characterId)
}