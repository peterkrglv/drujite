package com.example.data.repos

import android.util.Log
import com.example.data.api.GoalApi
import com.example.data.requests.IdRequest
import com.example.data.responces.GoalResponse
import com.example.domain.models.GoalModel
import com.example.domain.repos.GoalRepository
import com.example.domain.repos.SharedPrefsRepository

class GoalRepositoryImpl(
    private val goalApi: GoalApi,
    private val sharedPrefs: SharedPrefsRepository,

    ) : GoalRepository {
    override suspend fun getGoalsByCharacterId(characterId: Int): List<GoalModel> {
        val token = sharedPrefs.getUserToken()
        try {
            val goals = goalApi.getGoals(
                token = "Bearer $token",
                characterId = characterId
            )
            Log.d("server", "Fetched goals: ${goals.size}")
            return goals.map {
                it.toModel()
            }
        } catch (e: Exception) {
            Log.e("server", "Error fetching goals: ${e.message}")
            return emptyList()
        }
    }

    override suspend fun updateGoalStatus(goalId: Int, isCompleted: Boolean): Boolean {
        val token = sharedPrefs.getUserToken() ?: return false
        try {
            goalApi.completeGoal(
                token = token,
                goal = IdRequest(goalId)
            )
            Log.d("server", "Goal status updated: $goalId to $isCompleted")
            return true
        } catch (e: Exception) {
            Log.e("server", "Error updating goal status: ${e.message}")
            return false
        }
    }
}

private fun GoalResponse.toModel() =
    GoalModel(
        id = id,
        name = name,
        isCompleted = isCompleted
    )