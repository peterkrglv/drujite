package com.example.data.requests

data class SaveCharactersClothingRequest(
    val characterId: Int,
    val itemsIds: List<Int>
)