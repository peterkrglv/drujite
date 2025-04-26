package com.example.data.responces

data class CharacterResponse(
    val id: Int,
    val name: String,
    val player: String,
    val story: String,
    val clan: String,
    val imageUrl: String?
)