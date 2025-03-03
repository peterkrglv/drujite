package com.example.domain

interface SessionRepository {
    suspend fun getSessions(): List<SessionModel>
}