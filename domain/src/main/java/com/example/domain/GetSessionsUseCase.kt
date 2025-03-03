package com.example.domain

class GetSessionsUseCase(
    private val repo: SessionRepository
) {
    suspend fun execute() = repo.getSessions()
}