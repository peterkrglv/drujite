package com.example.data.responces

data class GoalResponse(
    val id: Int,
    val characterId: Int,
    val name: String,
    val isCompleted: Boolean,
)