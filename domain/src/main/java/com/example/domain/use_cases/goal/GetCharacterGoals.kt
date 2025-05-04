package com.example.domain.use_cases.goal

import com.example.domain.repos.GoalRepository

class GetCharacterGoals(
    private val repo: GoalRepository
) {
    suspend fun execute() = repo.getCharacterGoals()
}