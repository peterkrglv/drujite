package com.example.domain.use_cases.session

import com.example.domain.models.NewsModel
import com.example.domain.repos.NewsRepository
import com.example.domain.repos.SharedPrefsRepository

class GetSessionsNewsUseCase(
    private val sharedPrefs: SharedPrefsRepository,
    private val repo: NewsRepository
) {
    suspend fun execute(): List<NewsModel> {
        val sessionId = sharedPrefs.getSessionId()
        return repo.getNewsBySessionId(sessionId)
    }
}