package com.example.drujite.domain

class GetSessionsUseCase(
    private val repo: SessionRepository
) {
    suspend fun execute() = repo.getSessions()
}