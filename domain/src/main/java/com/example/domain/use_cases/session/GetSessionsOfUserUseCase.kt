package com.example.domain.use_cases.session

import com.example.domain.repos.SessionRepository

class GetSessionsOfUserUseCase(private val repo: SessionRepository) {
    suspend fun execute(userId: Int) = repo.getSessionsByUserId(userId)
}