package com.example.domain.use_cases

import com.example.domain.repos.SessionRepository

class GetSessionsUseCase(
    private val repo: SessionRepository
) {
    suspend fun execute() = repo.getSessions()
}