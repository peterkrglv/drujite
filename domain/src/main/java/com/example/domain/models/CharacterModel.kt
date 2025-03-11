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
    val character1 = CharacterModel(
        id = 1,
        name = "Персонаж",
        player = "Игрок",
        story = "тут будет квента",
        image = "",
        clan = "Янтарная Ветвь"
    )
    val character2 = CharacterModel(
        id = 2,
        name = "Персонаж 2",
        player = "Игрок 2",
        story = "",
        image = "",
        clan = "Ониксовая Ветвь"
    )
    return List(9) { character1 } + List(5) { character2 }
}