package com.example.domain.use_cases.session

import com.example.domain.models.TimetableModel
import com.example.domain.repos.SessionRepository
import com.example.domain.repos.SharedPrefsRepository

class GetTimeTableUseCase(
    private val sharedPrefs: SharedPrefsRepository,
    private val repo: SessionRepository
) {
    suspend fun execute(): TimetableModel {
        val sessionId = sharedPrefs.getSessionId()
        val date = "25.03.2025"
        val timeTable = repo.getTimetable(sessionId, date)
        return timeTable
    }
}