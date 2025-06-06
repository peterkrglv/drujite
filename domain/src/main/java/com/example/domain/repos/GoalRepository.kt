package com.example.domain.repos

import com.example.domain.models.GoalModel

interface GoalRepository {
    suspend fun getCharacterGoals(): List<GoalModel>
    suspend fun updateGoalStatus(goalId: Int, isCompleted: Boolean): Boolean
}