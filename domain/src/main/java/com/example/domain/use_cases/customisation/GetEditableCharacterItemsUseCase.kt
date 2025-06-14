package com.example.domain.use_cases.customisation

import com.example.domain.repos.CustomisationRepository

class GetEditableCharacterItemsUseCase (
    private val repo: CustomisationRepository
){
    suspend fun execute(characterId: Int): List<CustomisationOption> {
        return repo.getCharactersEditableItems(characterId)
    }
}