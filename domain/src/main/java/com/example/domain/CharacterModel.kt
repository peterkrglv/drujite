package com.example.domain

data class CharacterModel(
    val name: String,
    val player: String,
    val story: String,
    val image: String,
    val clan: String
)

fun getCharactersTest(): List<CharacterModel> {
    val character1 = CharacterModel(
        name = "Персонаж",
        player = "Игрок",
        story = "тут будет квента",
        image = "",
        clan = "Янтарная Ветвь"
    )
    val character2 = CharacterModel(
        name = "Персонаж 2",
        player = "Игрок 2",
        story = "",
        image = "",
        clan = "Ониксовая Ветвь"
    )
    return List(9) { character1 } + List(5) { character2 }
}