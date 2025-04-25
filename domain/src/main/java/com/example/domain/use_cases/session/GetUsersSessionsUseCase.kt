package com.example.domain.use_cases.session

import com.example.domain.repos.SessionRepository

class GetUsersSessionsUseCase(
    private val repo: SessionRepository
) {
    suspend fun execute() = repo.getUsersSessions()
}