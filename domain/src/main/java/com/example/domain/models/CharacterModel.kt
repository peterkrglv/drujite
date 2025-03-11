package com.example.domain.models

data class CharacterModel(
    val id: Int,
    val name: String,
    val player: String,
    val story: String,
    val image: String,
    val clan: String
)

fun getCharactersTest(): List<CharacterModel> {
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
        ),
        CharacterModel(
            id = 5,
            name = "Персонаж 10",
            player = "Игрок 2",
            story = "",
            image = "",
            clan = "Вивианитовая ветвь"
        ),
        CharacterModel(
            id = 6,
            name = "Персонаж 11",
            player = "Игрок 2",
            story = "",
            image = "",
            clan = "Вивианитовая ветвь"
        ),
        CharacterModel(
            id = 7,
            name = "Персонаж 12",
            player = "Игрок 2",
            story = "",
            image = "",
            clan = "Вивианитовая ветвь"
        ),
        CharacterModel(
            id = 8,
            name = "Персонаж 3",
            player = "Игрок 1",
            story = "",
            image = "",
            clan = "Яшмовая ветвь"
        )
    )
}