package com.example.domain.use_cases

import com.example.domain.repos.CharacterRepository

class SaveCharacterCustomImageUseCase(private val repo: CharacterRepository) {
    suspend fun execute(characterId: Int, chosenOptions: Map<CustomisationCategory, Int>): Boolean {
        //generate the image from images i have and save it
        //or maybe make the server do this...
        return true
    }
}