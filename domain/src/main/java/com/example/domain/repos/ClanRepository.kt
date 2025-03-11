package com.example.domain.repos

import com.example.domain.models.ClanModel

interface ClanRepository {
    suspend fun getClans(sessionId: Int): List<ClanModel>
}