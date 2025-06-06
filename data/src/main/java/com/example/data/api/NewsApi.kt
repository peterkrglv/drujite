package com.example.data.api

import com.example.data.responces.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsApi {
    @GET("news/session")
    suspend fun getNewsBySessionId(
        @Header("Authorization") token: String,
        @Query("sessionId") sessionId: Int
    ): List<NewsResponse>
}