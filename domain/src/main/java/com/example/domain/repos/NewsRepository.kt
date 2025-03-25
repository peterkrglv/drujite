package com.example.domain.repos

import com.example.domain.models.NewsModel

interface NewsRepository {
    suspend fun getNewsBySessionId(sessionId: Int): List<NewsModel>
}