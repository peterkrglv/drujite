package com.example.data.test

import com.example.domain.repos.CustomisationRepository
import com.example.domain.use_cases.character.CustomisationCategory
import com.example.domain.use_cases.character.CustomisationOption


class CustomisationRepositoryTest : CustomisationRepository {
    override suspend fun getCustomisationOptions(): List<CustomisationCategory> {
        val categories = listOf(
            CustomisationCategory(
                id = 1,
                name = "Волосы",
                priority = 1,
                x = 0,
                y = 0,
                options = listOf(
                    CustomisationOption(id = 1, imageUrl = "hair1.png"),
                    CustomisationOption(id = 2, imageUrl = "hair2.png")
                )
            ),
            CustomisationCategory(
                id = 2,
                name = "Лицо",
                priority = 2,
                x = 0,
                y = 0,
                options = listOf(
                    CustomisationOption(id = 3, imageUrl = "face1.png"),
                    CustomisationOption(id = 4, imageUrl = "face2.png")
                )
            )
        )
        return categories
    }
}