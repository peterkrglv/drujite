package com.example.drujite.domain

interface SessionRepository {
    suspend fun getSessions(): List<SessionModel>
}