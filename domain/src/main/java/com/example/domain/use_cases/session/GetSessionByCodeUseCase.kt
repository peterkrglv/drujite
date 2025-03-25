package com.example.domain.use_cases.session

import com.example.domain.repos.SessionRepository

class GetSessionByCodeUseCase(private val repo: SessionRepository) {
    suspend fun execute(code: String) = repo.getSessionByCode(code)
}