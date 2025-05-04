package com.example.data.`test-repos`

import com.example.domain.models.GoalModel
import com.example.domain.repos.GoalRepository

class GoalRepositoryTest : GoalRepository {
    override suspend fun getCharacterGoals(): List<GoalModel> {
        return listOf(
            GoalModel(1, "Найти артефакт Слытко", true),
            GoalModel(
                2,
                "Узнать тайны директрисы Колдовстворца - Хозяйки Медной горы",
                false
            ),
            GoalModel(
                3,
                "Раскрыть тайну природы теней, пришедших в школу вслед за певцами теней",
                false
            )
        )
    }

    override suspend fun updateGoalStatus(goalId: Int, isCompleted: Boolean): Boolean {
        return true
    }

}