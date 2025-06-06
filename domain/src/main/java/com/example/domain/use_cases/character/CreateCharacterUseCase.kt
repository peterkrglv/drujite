package com.example.domain.use_cases.character

import com.example.domain.repos.CharacterRepository
import com.example.domain.repos.SessionRepository
import com.example.domain.repos.SharedPrefsRepository
class CreateCharacterUseCase(
    private val characterRepo: CharacterRepository,
    private val sessionRepo: SessionRepository,
    private val sharedPrefs: SharedPrefsRepository
) {
    suspend fun execute(name: String, story: String, clanId: Int, sessionId: Int): Int? {
        val characterId = characterRepo.addCharacter(name, story, clanId)
        if (characterId != null) {
            sessionRepo.addCharacterToSession(sessionId, characterId)
            sharedPrefs.saveCharacterId(characterId)
        }
        return characterId
    }
}