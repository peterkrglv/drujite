package com.example.domain.use_cases.character

import com.example.domain.repos.CharacterRepository
import com.example.domain.repos.SessionRepository
import com.example.domain.repos.SharedPrefsRepository

class CreateCharacterUseCase(
    private val repo: CharacterRepository,
    private val sessionRepo: SessionRepository,
    private val sharedPrefs: SharedPrefsRepository
) {
    suspend fun execute(userId: Int, name: String, clanId: Int, sessionId: Int): Int? {
        val characterId = repo.addCharacter(userId, name, clanId)
        if (characterId != null) {
            sessionRepo.addCharacterToSession(characterId, characterId)
            sharedPrefs.saveCharacterId(characterId)
        }
        return characterId
    }
}