package com.example.domain.repos

import com.example.domain.models.SessionModel

interface SessionRepository {
    suspend fun getSessionById(id: Int): SessionModel?
    suspend fun getSessions(): List<SessionModel>
    suspend fun getSessionsByUserId(userId: Int): List<SessionModel>
    suspend fun getSessionByCode(code: String): SessionModel?
    suspend fun addCharacterToSession(sessionId: Int, characterId: Int): Boolean
}