package com.example.domain.use_cases.session

import com.example.domain.models.SessionModel
import com.example.domain.repos.SessionRepository

class GetCurrentSessionUseCase(
    private val repo: SessionRepository
) {
    suspend fun execute(): SessionModel? {
        return repo.getCurrentSession()
    }
}