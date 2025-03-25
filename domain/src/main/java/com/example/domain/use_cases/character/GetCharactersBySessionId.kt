package com.example.domain.use_cases.character

import com.example.domain.repos.CharacterRepository

class GetCharactersBySessionId(val repo: CharacterRepository) {
    suspend fun execute(sessionId: Int) = repo.getCharactersBySessionId(sessionId)

}