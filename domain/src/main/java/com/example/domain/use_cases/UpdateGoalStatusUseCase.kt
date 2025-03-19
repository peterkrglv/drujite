package com.example.domain.use_cases

import com.example.domain.repos.GoalRepository

class UpdateGoalStatusUseCase(private val repo: GoalRepository) {
    suspend fun execute(goalId: Int, isCompleted: Boolean): Boolean {
        return repo.updateGoalStatus(goalId, isCompleted)
    }
}