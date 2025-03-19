package com.example.domain.use_cases

import com.example.domain.repos.GoalRepository

class GetGoalsByCharacterIdUseCase(
    private val repo: GoalRepository
) {
    suspend fun execute(characterId: Int) = repo.getGoalsByCharacterId(characterId)
}