package com.example.data

import com.example.domain.models.CharacterModel
import com.example.domain.repos.CharacterRepository

class CharacterReposirotyTest: CharacterRepository {
    override suspend fun addCharacter(userId: Int, name: String, clanId: Int): Int {
        return 1
    }

    override suspend fun getCharactersByUserId(userId: Int): List<CharacterModel> {
        return listOf(
            CharacterModel(
                id = 1,
                name = "Персонаж",
                player = "Игрок 1",
                story = "тут будет квента",
                imageUrl = "",
                clan = "Янтарная Ветвь"
            ),
            CharacterModel(
                id = 2,
                name = "Персонаж 2",
                player = "Игрок 1",
                story = "",
                imageUrl = "",
                clan = "Ониксовая Ветвь"
            ),
            CharacterModel(
                id = 3,
                name = "Персонаж 3",
                player = "Игрок 1",
                story = "",
                imageUrl = "",
                clan = "Яшмовая ветвь"
            ),
            CharacterModel(
                id = 4,
                name = "Персонаж 4",
                player = "Игрок 1",
                story = "",
                imageUrl = "",
                clan = "Вивианитовая ветвь"
            )
        )
    }

    override suspend fun getCharacterById(characterId: Int): CharacterModel {
        return CharacterModel(
            id = characterId,
            name = "Мирон Арестов",
            player = "Илья Коданёв",
            story = "Мирон истинный уроженец Гранатовой ветви. Будучи выращенным вблизи вулканов, он с детства познавал дикую магию, подвергался изнуряющим тренировкам и был свидетелем...\n\n\n\n\n\n\n\nОчень длинное поле",
            imageUrl = "",
            clan = "Гранатовая Ветвь"
        )
    }


}