package com.example.domain.repos

import com.example.domain.use_cases.customisation.CustomisationCategory
import com.example.domain.use_cases.customisation.CustomisationOption

interface CustomisationRepository {
    suspend fun getCustomisationOptions(): List<CustomisationCategory>
    suspend fun getCharactersItems(characterId: Int): List<CustomisationOption>
    suspend fun getCharactersEditableItems(characterId: Int): List<CustomisationOption>
    suspend fun saveCharactersItems(characterId: Int, itemsIds: List<Int>): Boolean
    suspend fun saveCharactersImage(characterId: Int, image: ByteArray): Boolean
}