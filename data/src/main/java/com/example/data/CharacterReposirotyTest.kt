package com.example.data

import com.example.domain.models.CharacterModel
import com.example.domain.repos.CharacterRepository

class CharacterReposirotyTest: CharacterRepository {
    override suspend fun addCharacter(userId: Int, name: String, clanId: Int): Int {
        return 1
    }

    override suspend fun getCharacterByUserId(userId: Int): List<CharacterModel> {
        return listOf(
            CharacterModel(
                id = 1,
                name = "Персонаж",
                player = "Игрок 1",
                story = "тут будет квента",
                image = "",
                clan = "Янтарная Ветвь"
            ),
            CharacterModel(
                id = 2,
                name = "Персонаж 2",
                player = "Игрок 1",
                story = "",
                image = "",
                clan = "Ониксовая Ветвь"
            ),
            CharacterModel(
                id = 3,
                name = "Персонаж 3",
                player = "Игрок 1",
                story = "",
                image = "",
                clan = "Яшмовая ветвь"
            ),
            CharacterModel(
                id = 4,
                name = "Персонаж 4",
                player = "Игрок 1",
                story = "",
                image = "",
                clan = "Вивианитовая ветвь"
            )
        )
    }
}