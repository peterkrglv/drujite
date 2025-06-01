package com.example.domain.use_cases.customisation

import com.example.domain.repos.CustomisationRepository

class SaveCharacterImageUseCase(
    private val repo: CustomisationRepository
) {
    suspend fun execute(characterId: Int, image: ByteArray): Boolean {
        return repo.saveCharactersImage(
            characterId = characterId,
            image = image,
        )
    }
}