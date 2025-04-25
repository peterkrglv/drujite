package com.example.domain.use_cases.session

import com.example.domain.models.TimetableModel
import com.example.domain.repos.SessionRepository
import com.example.domain.repos.SharedPrefsRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GetTimeTableUseCase(
    private val sharedPrefs: SharedPrefsRepository,
    private val repo: SessionRepository
) {
    suspend fun execute(): TimetableModel {
        val sessionId = sharedPrefs.getSessionId()
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val timeTable = repo.getTimetable(sessionId, date)
        return timeTable
    }
}