package com.example.domain.use_cases.session

import com.example.domain.repos.ClanRepository

class GetClansBySessionIdUseCase(private val repo: ClanRepository) {
    suspend fun execute(sessionId: Int) = repo.getClans(sessionId)
}