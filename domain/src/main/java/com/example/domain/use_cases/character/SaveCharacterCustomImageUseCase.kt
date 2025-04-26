package com.example.domain.use_cases.character

import com.example.domain.repos.CharacterRepository

class SaveCharacterCustomImageUseCase(private val repo: CharacterRepository) {
    suspend fun execute(characterId: Int, chosenOptions: Map<CustomisationCategory, Int>): Boolean {
        return true
    }
}