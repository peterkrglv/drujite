package com.example.domain.use_cases.character

import com.example.domain.repos.CharacterRepository

class GetUsersCharactersUseCase(private val repo: CharacterRepository) {
    suspend fun execute(userToken: String) = repo.getUsersCharacters(userToken)
}