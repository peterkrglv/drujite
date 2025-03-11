package com.example.domain.repos

import com.example.domain.models.ClanModel
import com.example.domain.models.SessionModel

interface SessionRepository {
    suspend fun getSessionById(id: Int): SessionModel?
    suspend fun getSessions(): List<SessionModel>
    suspend fun getSessionByCode(code: String): SessionModel?
}