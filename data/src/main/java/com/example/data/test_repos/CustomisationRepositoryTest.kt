package com.example.data.test_repos

import com.example.domain.repos.CustomisationRepository
import com.example.domain.use_cases.customisation.CustomisationCategory
import com.example.domain.use_cases.customisation.CustomisationOption


class CustomisationRepositoryTest : CustomisationRepository {
    override suspend fun getCustomisationOptions(): List<CustomisationCategory> {
        val categories = emptyList<CustomisationCategory>()
        return categories
    }

    override suspend fun getCharactersItems(characterId: Int): List<CustomisationOption> {
        return emptyList()
    }

    override suspend fun getCharactersEditableItems(characterId: Int): List<CustomisationOption> {
        return emptyList()
    }

    override suspend fun saveCharactersItems(characterId: Int, itemsIds: List<Int>): Boolean {
        return true
    }

    override suspend fun saveCharactersImage(characterId: Int, image: ByteArray): Boolean {
        return true
    }
}