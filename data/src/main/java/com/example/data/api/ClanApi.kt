package com.example.data.api

import com.example.data.responces.ClanResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ClanApi {
    @GET("clan/session-all")
    suspend fun getClans(
        @Header("Authorization") token: String,
        @Query("sessionId") sessionId: Int
    ): List<ClanResponse>
}