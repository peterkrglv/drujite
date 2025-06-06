package com.example.domain.repos

import com.example.domain.models.SessionModel
import com.example.domain.models.TimetableModel

interface SessionRepository {
    suspend fun getCurrentSession(): SessionModel?
    suspend fun getUsersSessions(): List<SessionModel>
    suspend fun getSessionByCode(code: String): SessionModel?
    suspend fun addCharacterToSession(sessionId: Int, characterId: Int): Boolean
    suspend fun getTimetable(sessionId: Int, date: String): TimetableModel?
}