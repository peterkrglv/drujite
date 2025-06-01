package com.example.domain.use_cases.customisation

import com.example.domain.repos.CustomisationRepository

class SaveCharacterItemsUseCase(private val repo: CustomisationRepository) {
    suspend fun execute(characterId: Int, chosenOptions: Map<CustomisationCategory, Int>): Boolean {
        val optionsIds = chosenOptions.map { (category, option) ->
            category.items[option].id
        }
        return repo.saveCharactersItems(
            characterId = characterId,
            itemsIds = optionsIds.map { it },
        )
    }
}