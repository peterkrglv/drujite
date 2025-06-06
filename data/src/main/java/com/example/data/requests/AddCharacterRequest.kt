package com.example.data.requests

data class AddCharacterRequest(
    val name: String,
    val story: String,
    val clanId: Int,
    val image: String? = null
)